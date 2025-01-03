package com.ssaisriharsha.RestApi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class UserControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserNotFoundResponse> handleUserNotFound(UserNotFoundException e) {
        UserNotFoundResponse u = new UserNotFoundResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(u, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<InvalidIdResponse> handleInvalidId() {
        InvalidIdResponse response = new InvalidIdResponse(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResourceNotFoundResponse> handleResourceNotFound() {
        ResourceNotFoundResponse response = new ResourceNotFoundResponse(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserNotFoundResponse> handleAllExceptions(Exception e) {
        UserNotFoundResponse u = new UserNotFoundResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(u, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserExistsResponse> handleUserExistsException(UserExistsException e) {
        UserExistsResponse u = new UserExistsResponse(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(u, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<IllegalOperationResponse> handleIllegalOperationException(IllegalOperationException e) {
        IllegalOperationResponse res = new IllegalOperationResponse(e.getStatus(), e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(res, HttpStatus.valueOf(e.getStatus()));
    }
}
