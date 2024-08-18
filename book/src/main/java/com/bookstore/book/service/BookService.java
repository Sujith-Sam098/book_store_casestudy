package com.bookstore.book.service;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.exception.BookNotFound;
import com.bookstore.book.model.Book;
import com.bookstore.book.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public Book saveBook(BookDto bookDto) {
        Book book = new Book();
        book = bookDto.toBook(bookDto);
        return bookRepo.save(book);
    }

    public void delete(long id) {
        System.out.println("Book of id" + id + "deleted");
        bookRepo.deleteById(id);
    }

    public List<BookDto> findAll() {
        List<Book> bookList = bookRepo.findAll();
        List<BookDto> bookDtoList = bookList.stream().map(BookDto::fromBook).toList();
        return bookDtoList;
    }

    public BookDto findById(long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFound("Not found"));
        BookDto bookDto=BookDto.fromBook(book);
        return bookDto;
    }

}
