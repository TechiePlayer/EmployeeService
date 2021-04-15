package com.tp.employee.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(exception.getMessage(),exception.getLocalizedMessage(),new Date());
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception exception, WebRequest request) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(exception.getMessage(),exception.getLocalizedMessage(),new Date());
        return new ResponseEntity<>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
