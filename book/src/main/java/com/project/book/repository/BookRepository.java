package com.project.book.repository;

import com.project.book.entity.Book;
import com.project.book.entity.PublicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByName(String name);
}


