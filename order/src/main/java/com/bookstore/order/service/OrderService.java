package com.bookstore.order.service;

import com.bookstore.order.dto.BookDto;
import com.bookstore.order.exception.InsufficientsStockException;
import com.bookstore.order.exception.OrderNotFound;
import com.bookstore.order.feign.OrderFeign;
import com.bookstore.order.model.Order;
import com.bookstore.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.order.dto.OrderDto;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderFeign orderFeign;

    public Order saveOrder(OrderDto orderDto)
    {
        BookDto bookDto=orderFeign.getBook(orderDto.bookId());
        if(bookDto.quantity()>=orderDto.quantity())
        {
            throw new InsufficientsStockException("Insufficient Stocks for ordering");
        }
        Order order = orderDto.toOrder(orderDto);
        return orderRepo.save(order);
    }
    public void delete(long id) {
        System.out.println("Order of id" + id + "deleted");
        orderRepo.deleteById(id);
    }

    public List<OrderDto> findAll() {
        List<Order> orderList = orderRepo.findAll();
        List<OrderDto> orderDtoList = orderList.stream().map(order -> {
            BookDto bookDto = orderFeign.getBook(order.getBookId());
            return OrderDto.fromOrder(order,bookDto);
        }).toList();
        return orderDtoList;
    }

    public OrderDto findById(long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFound("Not found"));
        BookDto bookDto=orderFeign.getBook(order.getBookId());
        return OrderDto.fromOrder(order,bookDto);
    }
}
