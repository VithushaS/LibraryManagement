package com.example.LibraryManagement.lms;

public final class EndpointURI {
    private static final String BASE_API_PATH = "/api/";

    //URL for Book
    public static final String BOOK = BASE_API_PATH + "books";
    public static final String UPDATEBOOK = BOOK;
    public static final String BOOK_BY_ID = BOOK + "/{id}";

    public static final String BOOKS = BOOK;

    public static final String PATRON = BASE_API_PATH + "patrons";
    public static final String UPDATE_PATRON = PATRON;
    public static final String PATRON_BY_ID = PATRON + "/{id}";
    public static final String PATRONS =PATRON;
    public static final String BOOKID = "/{bookId}";
    public static final String PATRONID = "/{patronId}";
    public static final String BORROW = BASE_API_PATH + "borrow" + BOOKID + "/patron" + PATRONID;
}
