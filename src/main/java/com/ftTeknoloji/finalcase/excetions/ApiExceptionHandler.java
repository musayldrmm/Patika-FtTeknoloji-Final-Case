package com.ftTeknoloji.finalcase.excetions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PostErrorMessage.class)
    public final ResponseEntity<ExceptionsResponse> postErrorMessage(Exception exception, WebRequest request) {
        ExceptionsResponse exceptionResponse = new ExceptionsResponse(exception.getMessage(), 400);
        return new ResponseEntity<ExceptionsResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginErrorMessage.class)
    public final ResponseEntity<ExceptionsResponse> wrongErrorMessage(Exception exception, WebRequest request) {
        ExceptionsResponse exceptionResponse = new ExceptionsResponse(exception.getMessage(), 401);
        return new ResponseEntity<ExceptionsResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

}
