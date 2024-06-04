package com.assignment.demo.aop;


import com.assignment.demo.dto.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApplicationExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ExceptionString = "Exception -> {}";


    @ExceptionHandler(AssignmentException.class)
    public ResponseEntity<ExceptionDto> assignmentException(AssignmentException exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> genericException(Exception exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
