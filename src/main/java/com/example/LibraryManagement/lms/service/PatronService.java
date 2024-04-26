package com.example.LibraryManagement.lms.service;

import com.example.LibraryManagement.lms.dto.BookResponse;
import com.example.LibraryManagement.lms.dto.PatronRequest;
import com.example.LibraryManagement.lms.dto.PatronResponse;

import java.util.List;

public interface PatronService {
    void createPatron(PatronRequest patronRequest);

    boolean existsByMemberNo(String memberNo);

    boolean existsByPatronId(Long id);

    boolean checkUpdateExistsMemberNo(String memberNo, Long id);

    PatronResponse getByPatronId(Long id);

    List<PatronResponse> getAllPatrons();

    void deletePatron(Long id);
}
