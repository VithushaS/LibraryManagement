package com.example.LibraryManagement.lms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    @OneToMany(mappedBy = "book")
    private List<BorrowingRecord> borrowingRecords;

}
