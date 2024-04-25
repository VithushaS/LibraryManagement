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
}