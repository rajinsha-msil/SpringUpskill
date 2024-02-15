package com.project.book.service;

import com.project.book.entity.Book;
import com.project.book.exception.BookNotFoundException;
import com.project.book.repository.BookRepository;
import com.project.book.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookServiceInterface{
    @Autowired
    private BookRepository repository;

    public Book saveBook(Request request) {
        Book book = Book.details(0,request.getName(),request.getAuthor());
        return repository.save(book);
    }

    public Book getBookbyId(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String deleteBookById(Integer id) {
        repository.deleteById(id);
        return "Deleted Book" + id;
    }

    public Book updateBookByName(Book book) throws BookNotFoundException {
        Book book_details = repository.findByName(book.getName());
        if (book_details != null) {
            book_details.setName(book.getName());
            book_details.setAuthor(book.getAuthor());
            return repository.save(book_details);
        }else{
            throw new BookNotFoundException("Book Not Found");
        }
    }

}
