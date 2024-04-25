package com.example.LibraryManagement.lms;

public final class EndpointURI {
    private static final String BASE_API_PATH = "/api/";

    //URL for Book
    public static final String BOOK = BASE_API_PATH + "books";
    public static final String UPDATEBOOK = BOOK;
    public static final String BOOK_BY_ID = BOOK + "/{id}";

    public static final String BOOKS = BOOK;
}
