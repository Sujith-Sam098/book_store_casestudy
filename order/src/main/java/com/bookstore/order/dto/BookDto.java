package com.bookstore.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public record BookDto(long id,long quantity) {
    public BookDto(long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
