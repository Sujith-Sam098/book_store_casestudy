package com.bookstore.order.controller;

import com.bookstore.order.exception.OrderNotFound;
import com.bookstore.order.model.Order;
import com.bookstore.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bookstore.order.dto.OrderDto;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public Order createOrder(@RequestBody OrderDto orderDto)
    {
        return orderService.saveOrder(orderDto);
    }
    
    @GetMapping
    public List<OrderDto> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable long id)
    {
        return orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id)
    {
        orderService.delete(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable long id,@RequestBody OrderDto bookDto)
    {
        OrderDto bookDto1=orderService.findById(id);
        if(bookDto1!=null)
        {
            return orderService.saveOrder(bookDto);
        }
        else {
            throw new OrderNotFound("Order is not available");
        }
    }
}
