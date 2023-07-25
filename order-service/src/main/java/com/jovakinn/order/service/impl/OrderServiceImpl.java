package com.jovakinn.order.service.impl;

import com.jovakinn.order.domain.dto.InventoryDTO;
import com.jovakinn.order.domain.enitties.Order;
import com.jovakinn.order.domain.enitties.OrderItem;
import com.jovakinn.order.domain.kafka.events.OrderPlacedEvent;
import com.jovakinn.order.domain.kafka.service.SenderService;
import com.jovakinn.order.domain.kafka.topics.Topics;
import com.jovakinn.order.domain.mapper.OrderMapper;
import com.jovakinn.order.exceptions.OrderNotInStockException;
import com.jovakinn.order.repository.OrderRepository;
import com.jovakinn.order.service.OrderService;
import com.jovakinn.order.domain.data.OrderRequest;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final SenderService<OrderPlacedEvent> senderService;

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = OrderNotInStockException.class)
    public String placeOrder(OrderRequest orderRequest) {
        final Order order = OrderMapper.mapToEntity(orderRequest);
        final List<String> skuCodes = mapToSkuCodes(order);

        final InventoryDTO[] result = getInventoryData(skuCodes);
        final Boolean allProductsInStock = checkAllInStock(result);

        handleSaving(order, allProductsInStock);

        return "Order successfully placed";
    }

    private List<String> mapToSkuCodes(Order order) {
        return order.getOrderItemList()
                .stream()
                .map(OrderItem::getSkuCode)
                .toList();
    }

    private InventoryDTO[] getInventoryData(List<String> skuCodes) {
        final Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");
        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())) {
            return webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryDTO[].class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)))
                    .block();
        } finally {
            inventoryServiceLookup.end();
        }
    }

    private void handleSaving(Order order, Boolean allProductsInStock) throws OrderNotInStockException {
        final Span handlingSaving = tracer.nextSpan().name("handlingSaving");
        try (Tracer.SpanInScope spanInScope = tracer.withSpan(handlingSaving.start())) {
            if (Boolean.TRUE.equals(allProductsInStock)) {
                orderRepository.saveAndFlush(order);
                senderService.sendMessage(Topics.Constants.NOTIFICATION, new OrderPlacedEvent(order.getOrderNumber()));
            } else {
                throw new OrderNotInStockException("Product is not in stock, please try again later");
            }
        } finally {
            handlingSaving.end();
        }
    }

    private Boolean checkAllInStock(InventoryDTO[] result) {
        return Objects.isNull(result) ? Boolean.FALSE :
                Arrays.stream(result).allMatch(InventoryDTO::isInStock);
    }
}
