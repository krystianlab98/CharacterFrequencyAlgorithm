package com.globallogic.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class FileNullHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileNullException.class)
    public ResponseEntity<Object> handleException(SentenceNullException ex, WebRequest webRequest){
        String response = ex.getMessage();
        System.err.println(Arrays.toString(ex.getStackTrace()));
        return handleExceptionInternal(ex,response, HttpHeaders.EMPTY,HttpStatus.BAD_REQUEST,webRequest);
    }
}
