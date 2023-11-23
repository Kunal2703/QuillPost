package com.quillpost.blogpostservice.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException ex){
        String message= ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorizedExceptionHandler(UnauthorizedException ex){
        String message= ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> httpClientErrorException(HttpClientErrorException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(message, ex.getStatusCode());
    }
}
