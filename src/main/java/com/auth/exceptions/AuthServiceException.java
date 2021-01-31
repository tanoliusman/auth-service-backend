package com.auth.exceptions;

import org.springframework.http.HttpStatus;

public class AuthServiceException extends Exception{

    private ErrorResponseEnum responseEnum;
    private HttpStatus httpStatus;
    public AuthServiceException(ErrorResponseEnum errorResponseEnum, HttpStatus httpStatus){
        this.responseEnum = errorResponseEnum;
        this.httpStatus = httpStatus;
    }

    public ErrorResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ErrorResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
