package com.bookstore.order.feign;

import com.bookstore.order.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Book",url = "http://localhost:8100/books")
public interface OrderFeign {

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable long id);
}
