package com.example.LibraryManagement.lms.service;

import com.example.LibraryManagement.lms.entities.Book;
import com.example.LibraryManagement.lms.entities.BorrowingRecord;
import com.example.LibraryManagement.lms.entities.Patron;
import com.example.LibraryManagement.lms.repositories.BookRepository;
import com.example.LibraryManagement.lms.repositories.BorrowingRecordRepository;
import com.example.LibraryManagement.lms.repositories.PatronRepository;
import com.example.LibraryManagement.lms.util.BookAlreadyBorrowedException;
import com.example.LibraryManagement.lms.util.BookNotBorrowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService{
@Autowired
    BookRepository bookRepository;
@Autowired
    PatronRepository patronRepository;
@Autowired
    BorrowingRecordRepository borrowingRecordRepository;


    @Override
    public void saveborrowBook(Long bookId, Long patronId) throws BookAlreadyBorrowedException {
/// Check if the book is already borrowed
        if (isBookBorrowed(bookId)) {
            throw new BookAlreadyBorrowedException("Book is already borrowed.");
        }

        // Retrieve the book and patron
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book ID."));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new IllegalArgumentException("Invalid patron ID."));

        // Create a new borrowing record
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(new Date());

        // Save the borrowing record
        borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public void returnBook(Long bookId, Long patronId) throws BookNotBorrowedException {
        // Find the borrowing record for the given book and patron
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new BookNotBorrowedException("Book is not borrowed."));

        // Record the return date
        borrowingRecord.setReturnDate(new Date());

        // Save the updated borrowing record
        borrowingRecordRepository.save(borrowingRecord);
    }

    private boolean isBookBorrowed(Long bookId) {
        return borrowingRecordRepository.existsByBookIdAndReturnDateIsNull(bookId);
    }
}
