package com.example.LibraryManagement.lms.util;

public class BookAlreadyBorrowedException extends RuntimeException{
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}
