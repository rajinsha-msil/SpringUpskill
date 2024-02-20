package com.project.book.service;

import com.project.book.entity.Book;
import com.project.book.entity.Domain;
import com.project.book.entity.PublicationDetails;
import com.project.book.exception.BookNotFoundException;
import com.project.book.request.DomainRequest;
import com.project.book.request.Request;

import java.util.List;

public interface BookServiceInterface {
    Book saveBook(Request request);
    Book getBookbyId(Integer id);
    String deleteBookById(Integer id);
    Book updateBookByName(Book book) throws BookNotFoundException;

    PublicationDetails SavePublicationDetails(Request request);
    Domain SaveDomainDetails(DomainRequest request);

    List<Book> GetBookDetails();

}
