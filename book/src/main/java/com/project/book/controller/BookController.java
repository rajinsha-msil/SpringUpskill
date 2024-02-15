package com.project.book.controller;

import com.project.book.entity.Book;
import com.project.book.exception.BookNotFoundException;
import com.project.book.request.Request;
import com.project.book.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {
    @Autowired
    private BookServiceImpl service;
    @PostMapping("/saveBookDetails")
    public Book SaveBookDetails(@RequestBody @Valid Request request)
    {
        return service.saveBook(request);
    }
    @GetMapping("/getBookDetailsById/{id}")
    public Book FindBookById(@PathVariable Integer id){
        return service.getBookbyId(id);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public String DeleteBookById(@PathVariable Integer id){
        return service.deleteBookById(id);
    }

    @PutMapping("/updateBookByName")
    public Book UpdateBookByName(@RequestBody Book book) throws BookNotFoundException {
        return service.updateBookByName(book);
    }
}
