package com.jovakinn.order.exceptions;

public class OrderNotInStockException extends Exception {
    public OrderNotInStockException(String message) {
        super(message);
    }
}
