package com.example.LibraryManagement.lms.service;

import com.example.LibraryManagement.lms.util.BookAlreadyBorrowedException;
import com.example.LibraryManagement.lms.util.BookNotBorrowedException;

public interface BorrowingRecordService {


    void saveborrowBook(Long bookId, Long patronId) throws BookAlreadyBorrowedException;

    void returnBook(Long bookId, Long patronId)throws BookNotBorrowedException;
}
