package com.auth.exceptions.handler;

import com.auth.dto.ApiError;
import com.auth.dto.ApiValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        ApiError error = new ApiError(BAD_REQUEST, "validation error", ex);
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.getSubErrors().add(new ApiValidationError(ex.getObjectName(),fieldError.getField(),fieldError.getRejectedValue(),fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }


}
