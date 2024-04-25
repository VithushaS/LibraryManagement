package com.example.LibraryManagement.lms.service;

import com.example.LibraryManagement.lms.dto.BookRequest;
import com.example.LibraryManagement.lms.dto.BookResponse;

import java.util.List;

public interface BookService {
    void createBook(BookRequest bookRequest);

    boolean existsByBookId(Long id);

    BookResponse getByBookId(Long id);

    List<BookResponse> getAllBooks();

    void deleteBook(Long id);

    boolean existsByTitleAndAuthor(String title, String author);

    boolean checkUpdateExistsByTitle(String title, String author,Long id);
}
