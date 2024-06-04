package com.assignment.demo.aop;

public class AssignmentException extends RuntimeException {

    public AssignmentException(String message) {
        this(message, null);
    }

    public AssignmentException(String message, Throwable throwable) {
        super(message, throwable);

    }
}
