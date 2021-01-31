package com.auth.exceptions.handler;

import com.auth.dto.ApiError;
import com.auth.exceptions.AuthServiceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class AuthServiceExceptionHandler {


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(AuthServiceException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            AuthServiceException ex) {
        ApiError error = new ApiError(ex.getHttpStatus(),ex.getResponseEnum().getReason(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            SQLIntegrityConstraintViolationException ex) {
        ApiError error = new ApiError(BAD_REQUEST,ex.getMessage(), ex);
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }
}