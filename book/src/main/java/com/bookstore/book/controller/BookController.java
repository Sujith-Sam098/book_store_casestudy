package com.bookstore.book.controller;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.exception.BookNotFound;
import com.bookstore.book.model.Book;
import com.bookstore.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody BookDto bookDto)
    {
        return bookService.saveBook(bookDto);
    }

    @GetMapping
    public List<BookDto> findAll(){
    return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto findById(@PathVariable long id)
    {
        return bookService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id)
    {
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id,@RequestBody BookDto bookDto)
    {
        BookDto bookDto1=bookService.findById(id);
        if(bookDto1!=null)
        {
            return bookService.saveBook(bookDto);
        }
        else {
            throw new BookNotFound("Book is not available");
        }
    }
}
