package com.example.LibraryManagement.lms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Patron{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String memberNo;
    private int mobileNo;
    private String address;
    @OneToMany(mappedBy = "patron")
    private List<BorrowingRecord> borrowingRecords;

}
