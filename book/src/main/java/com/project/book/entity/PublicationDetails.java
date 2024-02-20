package com.project.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publication_details")
@Builder
public class PublicationDetails {
        @Id
        @GeneratedValue
        private int id;
        private String published_by;
        private int no_of_copies;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "book_id")
        private Book book;
}
