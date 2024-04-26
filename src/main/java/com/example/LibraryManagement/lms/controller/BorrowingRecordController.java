package com.example.LibraryManagement.lms.controller;

import com.example.LibraryManagement.lms.EndpointURI;
import com.example.LibraryManagement.lms.common.BaseResponse;
import com.example.LibraryManagement.lms.enums.RequestStatus;
import com.example.LibraryManagement.lms.service.BookService;
import com.example.LibraryManagement.lms.service.BorrowingRecordService;
import com.example.LibraryManagement.lms.service.PatronService;
import com.example.LibraryManagement.lms.util.BookAlreadyBorrowedException;
import com.example.LibraryManagement.lms.util.BookNotBorrowedException;
import com.example.LibraryManagement.lms.util.StatusCodeBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BorrowingRecordController {
    @Autowired
    private StatusCodeBundle statusCodeBundle;
    @Autowired
    private BorrowingRecordService borrowingRecordService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PatronService patronService;
    @PostMapping(value = EndpointURI.BORROW)
    public ResponseEntity<Object> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        if (!bookService.existsByBookId(bookId)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getBookNotExistsCode(), statusCodeBundle.getBookNotExistsMessage()));
        }
        if (!patronService.existsByPatronId(patronId)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getPatronNotExistsCode(), statusCodeBundle.getPatronNotExistsMessage()));
        }
        try {
        borrowingRecordService.saveborrowBook(bookId,patronId);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                statusCodeBundle.getCommonSuccessCode(),statusCodeBundle.getSaveBorrowBookSuccessMessage()));
        } catch (BookAlreadyBorrowedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book is already borrowed."); // Book is already borrowed
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid book ID or patron ID."); // Invalid book ID or patron ID
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Other internal server error
        }
    }
    @PutMapping(value = EndpointURI.BORROW)
    public ResponseEntity<Object> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        if (!bookService.existsByBookId(bookId)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getBookNotExistsCode(), statusCodeBundle.getBookNotExistsMessage()));
        }
        if (!patronService.existsByPatronId(patronId)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getPatronNotExistsCode(), statusCodeBundle.getPatronNotExistsMessage()));
        }
        try {
            // Record the return of the book
            borrowingRecordService.returnBook(bookId, patronId);
            return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                    statusCodeBundle.getCommonSuccessCode(), statusCodeBundle.getSaveReturnBookSuccessMessage()));
        } catch (BookNotBorrowedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book is not borrowed."); // Book is not borrowed
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid book ID or patron ID."); // Invalid book ID or patron ID
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Other internal server error
        }
    }
}
