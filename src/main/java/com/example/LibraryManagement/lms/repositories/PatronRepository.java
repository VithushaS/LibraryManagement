package com.example.LibraryManagement.lms.repositories;

import com.example.LibraryManagement.lms.entities.Book;
import com.example.LibraryManagement.lms.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron,Long> {
    boolean existsByMemberNo(String memberNo);

    boolean existsByMemberNoAndIdNot(String memberNo, Long id);
}
