package com.example.LibraryManagement.lms.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:MessagesAndCodes.properties")
@Getter
@Setter
public class StatusCodeBundle {
    // Common Success code
    @Value("${code.success.common}")
    private String commonSuccessCode;

    @Value("${message.success.save.book}")
    private String saveBookSuccessMessage;

    @Value("${message.success.update.book}")
    private String updateBookSuccessMessage;
    @Value("${code.validation.book.notExists}")
    private String bookNotExistsCode;

    @Value("${message.validation.book.notExists}")
    private String bookNotExistsMessage;

    @Value("${message.success.getById.book}")
    private String bookByIdSuccessMessage;

    @Value("${message.success.getAll.books}")
    private String getAllBooksSuccessMessage;

    @Value("${message.success.deleteById.book}")
    private String deleteBookSuccessMessage;
    @Value("${message.validation.book.title.already.exists}")
    private String bookTitleAlreadyExistsMessage;
    @Value("${code.validation.book.title.already.exists}")
    private String validationCodeBookTitleAlreadyExists;

    @Value("${message.success.save.patron}")
    private String savePatronSuccessMessage;

    @Value("${code.validation.patron.memberNo.already.exists}")
    private String validationCodePatronMemberNoAlreadyExists;
    @Value("${message.validation.patron.memberNo.already.exists}")
    private String patronMemberNoAlreadyExistsMessage;
    @Value("${message.success.update.patron}")
    private String updatePatronSuccessMessage;

    @Value("${code.validation.patron.notExists}")
    private String patronNotExistsCode;

    @Value("${message.validation.patron.notExists}")
    private String patronNotExistsMessage;
    @Value("${message.success.getById.patron}")
    private String patronByIdSuccessMessage;
    @Value("${message.success.getAll.patrons}")
    private String getAllPatronSuccessMessage;
    @Value("${message.success.delete.patron}")
    private String deletePatronSuccessMessage;

    @Value("${message.success.save.borrow_book}")
    private String saveBorrowBookSuccessMessage;
    @Value("${message.success.save.return_book}")
    private String saveReturnBookSuccessMessage;
}