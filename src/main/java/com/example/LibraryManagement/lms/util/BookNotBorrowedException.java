package com.example.LibraryManagement.lms.util;

public class BookNotBorrowedException extends RuntimeException {
    public BookNotBorrowedException(String message) {
        super(message);
    }
}
