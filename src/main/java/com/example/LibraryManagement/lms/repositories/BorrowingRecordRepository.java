package com.example.LibraryManagement.lms.repositories;

import com.example.LibraryManagement.lms.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
    Optional<BorrowingRecord> findByBookId(Long bookId);

    boolean existsByBookIdAndReturnDateIsNull(Long bookId);

    Optional<BorrowingRecord> findByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);

}
