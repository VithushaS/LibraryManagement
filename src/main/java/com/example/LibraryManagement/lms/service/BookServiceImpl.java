package com.example.LibraryManagement.lms.service;

import com.example.LibraryManagement.lms.dto.BookRequest;
import com.example.LibraryManagement.lms.dto.BookResponse;
import com.example.LibraryManagement.lms.entities.Book;
import com.example.LibraryManagement.lms.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
@Autowired
private BookRepository bookRepository;
    @Override
    public void createBook(BookRequest bookRequest) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest,book);
        bookRepository.save(book);
    }

    @Override
    public boolean existsByBookId(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public BookResponse getByBookId(Long id) {
        BookResponse bookResponse = new BookResponse();
       Optional<Book>  book = bookRepository.findById(id);
       BeanUtils.copyProperties(book.get(),bookResponse);
        return bookResponse;
    }

    @Transactional
    public List<BookResponse> getAllBooks() {
        List<BookResponse> bookResponseList = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        for (Book book : bookList) {
            BookResponse bookResponse = new BookResponse();
            BeanUtils.copyProperties(book, bookResponse);
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsByTitleAndAuthor(String title, String author) {
        return bookRepository.existsByTitleAndAuthor(title,author);
    }

    @Override
    public boolean checkUpdateExistsByTitle(String title, String author, Long id) {
        return bookRepository.existsByTitleAndAuthorAndIdNot(title,author,id);
    }
}
