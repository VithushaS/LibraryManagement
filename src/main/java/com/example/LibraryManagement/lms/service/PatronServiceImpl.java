package com.example.LibraryManagement.lms.service;

import com.example.LibraryManagement.lms.dto.PatronRequest;
import com.example.LibraryManagement.lms.dto.PatronResponse;
import com.example.LibraryManagement.lms.entities.Patron;
import com.example.LibraryManagement.lms.repositories.PatronRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService{
    @Autowired
    PatronRepository patronRepository;
    @Override
    public void createPatron(PatronRequest patronRequest) {
        Patron patron = new Patron();
        BeanUtils.copyProperties(patronRequest,patron);
        patronRepository.save(patron);
    }

    @Override
    public boolean existsByMemberNo(String memberNo) {
        return patronRepository.existsByMemberNo(memberNo);
    }

    @Override
    public boolean existsByPatronId(Long id) {
        return patronRepository.existsById(id);
    }

    @Override
    public boolean checkUpdateExistsMemberNo(String memberNo, Long id) {
        return patronRepository.existsByMemberNoAndIdNot(memberNo,id);
    }

    @Override
    public PatronResponse getByPatronId(Long id) {
        PatronResponse patronResponse = new PatronResponse();
        Optional<Patron> patron = patronRepository.findById(id);
        BeanUtils.copyProperties(patron.get(),patronResponse);
        return patronResponse;
    }

    @Override
    public List<PatronResponse> getAllPatrons() {
        List<PatronResponse> patronResponseList = new ArrayList<>();
        List<Patron> patronList = patronRepository.findAll();
        for (Patron patron : patronList) {
            PatronResponse patronResponse = new PatronResponse();
            BeanUtils.copyProperties(patron, patronResponse);
            patronResponseList.add(patronResponse);
        }
        return patronResponseList;
    }

    @Override
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
