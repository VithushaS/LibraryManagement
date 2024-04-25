package com.example.LibraryManagement.lms.repositories;

import com.example.LibraryManagement.lms.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
