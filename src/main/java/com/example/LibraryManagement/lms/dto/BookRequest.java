package com.example.LibraryManagement.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
}
