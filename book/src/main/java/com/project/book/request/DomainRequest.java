package com.project.book.request;

import com.project.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainRequest {
    private String domain_name;
    private List<Book> books;
}
