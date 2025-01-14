package com.mjc.school.service.exception;

public class NewsServiceException extends RuntimeException{
    private final String errorCode;

    public NewsServiceException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
