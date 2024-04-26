package com.example.LibraryManagement.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronResponse {
    private Long id;
    private String name;
    private String memberNo;
    private int mobileNo;
    private String address;
}
