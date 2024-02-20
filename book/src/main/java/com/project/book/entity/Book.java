package com.project.book.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@AllArgsConstructor(staticName = "details")
@NoArgsConstructor
@Table(name = "book_details")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String name;
    private String author;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Author author_details;*/
}
