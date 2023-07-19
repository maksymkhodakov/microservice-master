package com.jovakinn.order.service.impl;

import com.jovakinn.order.domain.dto.InventoryDTO;
import com.jovakinn.order.domain.enitties.Order;
import com.jovakinn.order.domain.enitties.OrderItem;
import com.jovakinn.order.domain.mapper.OrderMapper;
import com.jovakinn.order.exceptions.OrderNotInStockException;
import com.jovakinn.order.repository.OrderRepository;
import com.jovakinn.order.service.OrderService;
import com.jovakinn.order.domain.data.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    @Override
    @Transactional
    public void placeOrder(OrderRequest orderRequest) throws OrderNotInStockException {
        final Order order = OrderMapper.mapToEntity(orderRequest);
        final List<String> skuCodes = mapToSkuCodes(order);

        final InventoryDTO[] result = getInventoryData(skuCodes);
        final Boolean allProductsInStock = checkAllInStock(result);

        handleSaving(order, allProductsInStock);
    }

    private List<String> mapToSkuCodes(Order order) {
        return order.getOrderItemList()
                .stream()
                .map(OrderItem::getSkuCode)
                .toList();
    }

    private InventoryDTO[] getInventoryData(List<String> skuCodes) {
        return webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryDTO[].class)
                .block();
    }

    private void handleSaving(Order order, Boolean allProductsInStock) throws OrderNotInStockException {
        if (Boolean.TRUE.equals(allProductsInStock)) {
            orderRepository.saveAndFlush(order);
        } else {
            throw new OrderNotInStockException("Product is not in stock, please try again later");
        }
    }

    private Boolean checkAllInStock(InventoryDTO[] result) {
        return Objects.isNull(result) ? Boolean.FALSE :
                Arrays.stream(result).allMatch(InventoryDTO::isInStock);
    }
}
