Library Management System
Description
The Library Management System is a web-based application built using Spring Boot. It allows librarians to manage books, patrons, and borrowing records.

Prerequisites
Java Development Kit (JDK) 17 or higher
Gradle for building the project

API Endpoints
The following RESTful endpoints are available:

Books
GET /api/books: Retrieve a list of all books.
GET /api/books/{id}: Retrieve details of a specific book by ID.
POST /api/books: Add a new book to the library.
PUT /api/books: Update an existing book's information.
DELETE /api/books/{id}: Remove a book from the library.
Patrons
GET /api/patrons: Retrieve a list of all patrons.
GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
POST /api/patrons: Add a new patron to the system.
PUT /api/patrons: Update an existing patron's information.
DELETE /api/patrons/{id}: Remove a patron from the system.
Borrowing
POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
