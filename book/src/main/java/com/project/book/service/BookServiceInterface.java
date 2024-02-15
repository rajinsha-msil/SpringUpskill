package com.project.book.service;

import com.project.book.entity.Book;
import com.project.book.exception.BookNotFoundException;
import com.project.book.request.Request;

public interface BookServiceInterface {
    Book saveBook(Request request);
    Book getBookbyId(Integer id);
    String deleteBookById(Integer id);
    Book updateBookByName(Book book) throws BookNotFoundException;



}
