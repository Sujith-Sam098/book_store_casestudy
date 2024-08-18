package com.bookstore.order.dto;


import com.bookstore.order.dto.BookDto;
import com.bookstore.order.model.Order;

public record OrderDto(long id,
                       long custId,
                       long bookId,
                       long quantity,
                       String status,
                       BookDto dto) {
    public static OrderDto fromOrder(Order order,BookDto dto)
    {
        OrderDto orderDto=new OrderDto(order.getId(),order.getCustId(),order.getBookId(),order.getQuantity(),order.getStatus(),dto);
        return orderDto;
    }
    public Order toOrder(OrderDto orderDto)
    {
        Order order=new Order();
        order.setCustId(orderDto.custId());
        order.setBookId(orderDto.bookId());
        order.setQuantity(orderDto.quantity());
        order.setStatus(orderDto.status());
        return order;
    }
}
