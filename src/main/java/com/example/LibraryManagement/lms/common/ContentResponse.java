package com.example.LibraryManagement.lms.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.HashMap;
@Getter
@Setter
public class ContentResponse<T> extends BaseResponse{
    private Map<String, T> result = new HashMap<>();

    public ContentResponse(String key, T dto, String status, String statusCode, String message) {
        super(status, statusCode, message);
        result.put(key, dto);
    }
}
