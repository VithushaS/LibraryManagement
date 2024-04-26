package com.example.LibraryManagement.lms.controller;

import com.example.LibraryManagement.lms.EndpointURI;
import com.example.LibraryManagement.lms.common.BaseResponse;
import com.example.LibraryManagement.lms.common.ContentResponse;
import com.example.LibraryManagement.lms.dto.PatronRequest;
import com.example.LibraryManagement.lms.enums.RequestStatus;
import com.example.LibraryManagement.lms.service.PatronService;
import com.example.LibraryManagement.lms.util.Constants;
import com.example.LibraryManagement.lms.util.StatusCodeBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PatronController {
    @Autowired
    StatusCodeBundle statusCodeBundle;
    @Autowired
    PatronService patronService;

    @PostMapping(value = EndpointURI.PATRON)
    public ResponseEntity<Object> savePatron(@RequestBody PatronRequest patronRequest){
        if(patronService.existsByMemberNo(patronRequest.getMemberNo())){
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getValidationCodePatronMemberNoAlreadyExists(),
                    statusCodeBundle.getPatronMemberNoAlreadyExistsMessage()));
        }
        patronService.createPatron(patronRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                statusCodeBundle.getCommonSuccessCode(),statusCodeBundle.getSavePatronSuccessMessage()));
    }

    @PutMapping(value = EndpointURI.UPDATE_PATRON)
    public ResponseEntity<Object> updatePatron(@RequestBody PatronRequest patronRequest) {
        if (!patronService.existsByPatronId(patronRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getPatronNotExistsCode(), statusCodeBundle.getPatronNotExistsMessage()));
        }
        if(patronService.checkUpdateExistsMemberNo(patronRequest.getMemberNo(),patronRequest.getId())){
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getValidationCodePatronMemberNoAlreadyExists(),
                    statusCodeBundle.getPatronMemberNoAlreadyExistsMessage()));
        }
        patronService.createPatron(patronRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                statusCodeBundle.getCommonSuccessCode(), statusCodeBundle.getUpdatePatronSuccessMessage()));
    }
    @GetMapping(value = EndpointURI.PATRON_BY_ID)
    public ResponseEntity<Object> getPatronById(@PathVariable Long id){
        if (!patronService.existsByPatronId(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getPatronNotExistsCode(), statusCodeBundle.getPatronNotExistsMessage()));
        }
        return ResponseEntity
                .ok(new ContentResponse<>(Constants.PATRON,patronService.getByPatronId(id),
                        RequestStatus.SUCCESS.getStatus(),statusCodeBundle.getCommonSuccessCode(),
                        statusCodeBundle.getPatronByIdSuccessMessage()));
    }
    @GetMapping(value = EndpointURI.PATRONS)
    public ResponseEntity<Object> getAllPatron() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.PATRON, patronService.getAllPatrons(),
                RequestStatus.SUCCESS.getStatus(), statusCodeBundle.getCommonSuccessCode(),
                statusCodeBundle.getGetAllPatronSuccessMessage()));
    }
    @DeleteMapping(value = EndpointURI.PATRON_BY_ID)
    public ResponseEntity<Object> deletePatron(@PathVariable Long id) {
        if (!patronService.existsByPatronId(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    statusCodeBundle.getPatronNotExistsCode(), statusCodeBundle.getPatronNotExistsMessage()));
        }
        patronService.deletePatron(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                statusCodeBundle.getCommonSuccessCode(), statusCodeBundle.getDeletePatronSuccessMessage()));
    }
}
