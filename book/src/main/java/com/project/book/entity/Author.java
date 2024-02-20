package com.project.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "author_details")
public class Author {
    @Id
    @GeneratedValue
    private int id;
    private String first_name;
    private String last_name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private List<Book> book;
}
