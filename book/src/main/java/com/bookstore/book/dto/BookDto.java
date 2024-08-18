package com.bookstore.book.dto;

import com.bookstore.book.model.Book;

public record BookDto(long id,
                      String bookName,
                      String bookAuthor,
                      long price,
                      long stocksAvail) {
    public static BookDto fromBook(Book book)
    {
        BookDto bookDto=new BookDto(book.getId(),book.getBookName(),book.getBookAuthor(),book.getPrice(),book.getStocksAvail());
        return bookDto;
    }
    public Book toBook(BookDto bookDto)
    {
        Book book=new Book();
        book.setBookName(bookDto.bookName());
        book.setBookAuthor(bookDto.bookAuthor());
        book.setPrice(bookDto.price());
        book.setStocksAvail(bookDto.stocksAvail());
        return book;
    }
}
