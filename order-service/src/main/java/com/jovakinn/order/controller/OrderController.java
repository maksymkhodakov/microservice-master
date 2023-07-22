package com.jovakinn.order.controller;

import com.jovakinn.order.domain.data.OrderRequest;
import com.jovakinn.order.exceptions.OrderNotInStockException;
import com.jovakinn.order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity<String>> placeOrder(@RequestBody OrderRequest orderRequest) throws OrderNotInStockException {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(orderService.placeOrder(orderRequest)));
    }

    public CompletableFuture<ResponseEntity<String>> fallbackMethod() {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Something went wrong!"));
    }

}
