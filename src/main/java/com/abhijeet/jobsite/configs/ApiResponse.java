package com.abhijeet.jobsite.configs;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private boolean success;
    private int statusCode;
    private T data;
    private String message;

    public ApiResponse(boolean success, int statusCode, T data, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    // Static factory method for success response
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, 200, data, null);
    }

    // Static factory method for success response
    public static <T> ApiResponse<T> success(T data, int statusCode) {
        return new ApiResponse<>(true, statusCode, data, null);
    }

    // Static factory method for success response
    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, HttpStatus.BAD_GATEWAY.value(), null, message);
    }

    // Static factory method for success response
    public static <T> ApiResponse<T> failure(int statusCode, String message) {
        return new ApiResponse<>(false, statusCode, null, message);
    }

    // Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

