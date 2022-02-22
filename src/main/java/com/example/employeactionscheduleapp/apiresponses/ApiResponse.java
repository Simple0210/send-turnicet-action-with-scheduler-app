package com.example.employeactionscheduleapp.apiresponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private Object object;
    private boolean success;

    public ApiResponse(Object object, boolean success) {
        this.object = object;
        this.success = success;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
