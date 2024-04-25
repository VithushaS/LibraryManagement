package com.example.LibraryManagement.lms.controller;

import com.example.LibraryManagement.lms.EndpointURI;
import com.example.LibraryManagement.lms.common.BaseResponse;
import com.example.LibraryManagement.lms.common.ContentResponse;
import com.example.LibraryManagement.lms.dto.BookRequest;
import com.example.LibraryManagement.lms.service.BookService;
import com.example.LibraryManagement.lms.util.Constants;
import com.example.LibraryManagement.lms.util.StatusCodeBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.LibraryManagement.lms.enums.RequestStatus;
@RestController
@CrossOrigin
public class BookController {
    @Autowired
    private StatusCodeBundle statusCodeBundle;
    @Autowired
    private BookService bookService;

    @PostMapping(value = EndpointURI.BOOK)
    public ResponseEntity<Object> saveBook(@RequestBody BookRequest bookRequest){
       if(bookService.existsByTitleAndAuthor(bookRequest.getTitle(),bookRequest.getAuthor())){
           return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                   statusCodeBundle.getValidationCodeBookTitleAlreadyExists(),
                   statusCodeBundle.getBookTitleAlreadyExistsMessage()));
       }
        bookService.createBook(bookRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                statusCodeBundle.getCommonSuccessCode(),statusCodeBundle.getSaveBookSuccessMessage()));
    }

    @PutMapping(value = EndpointURI.UPDATEBOOK)
    public ResponseEntity<Object> updateBook(@RequestBody BookRequest bookRequest) {
        if (!bookService.existsByBookId(bookRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getBookNotExistsCode(), statusCodeBundle.getBookNotExistsMessage()));
        }
        if(bookService.checkUpdateExistsByTitle(bookRequest.getTitle(),bookRequest.getAuthor(),bookRequest.getId())){
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getValidationCodeBookTitleAlreadyExists(),
                    statusCodeBundle.getBookTitleAlreadyExistsMessage()));
        }

            bookService.createBook(bookRequest);
            return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                    statusCodeBundle.getCommonSuccessCode(), statusCodeBundle.getUpdateBookSuccessMessage()));

    }
    @GetMapping(value = EndpointURI.BOOK_BY_ID)
    public ResponseEntity<Object> getBookById(@PathVariable Long id){
        if (!bookService.existsByBookId(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getBookNotExistsCode(), statusCodeBundle.getBookNotExistsMessage()));
        }
        return ResponseEntity
                .ok(new ContentResponse<>(Constants.BOOK,bookService.getByBookId(id),
                RequestStatus.SUCCESS.getStatus(),statusCodeBundle.getCommonSuccessCode(),
                statusCodeBundle.getBookByIdSuccessMessage()));
    }

    @GetMapping(value = EndpointURI.BOOKS)
    public ResponseEntity<Object> getAllBooks() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.BOOK, bookService.getAllBooks(),
                RequestStatus.SUCCESS.getStatus(), statusCodeBundle.getCommonSuccessCode(),
                statusCodeBundle.getGetAllBooksSuccessMessage()));
    }
    @DeleteMapping(value = EndpointURI.BOOK_BY_ID)
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        if (!bookService.existsByBookId(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getBookNotExistsCode(), statusCodeBundle.getBookNotExistsMessage()));
        }
        bookService.deleteBook(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                statusCodeBundle.getCommonSuccessCode(), statusCodeBundle.getDeleteBookSuccessMessage()));
    }
}
