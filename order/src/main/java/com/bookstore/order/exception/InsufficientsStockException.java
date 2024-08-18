package com.bookstore.order.exception;

public class InsufficientsStockException extends RuntimeException{
    public InsufficientsStockException(String message) {
        super(message);
    }
}
