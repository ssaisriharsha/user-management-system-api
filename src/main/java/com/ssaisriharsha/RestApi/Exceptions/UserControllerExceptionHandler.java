package com.ssaisriharsha.RestApi.Exceptions;

import com.ssaisriharsha.RestApi.Controller.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ControllerAdvice
public class UserControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityModel<UserNotFoundResponse>> handleUserNotFound(UserNotFoundException e) {
        UserNotFoundResponse u = new UserNotFoundResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        EntityModel<UserNotFoundResponse> uModel = EntityModel.of(u,
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(uModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<EntityModel<InvalidIdResponse>> handleInvalidId() {
        InvalidIdResponse response = new InvalidIdResponse(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
        EntityModel<InvalidIdResponse> responseEntityModel = EntityModel.of(response,
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(responseEntityModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<EntityModel<ResourceNotFoundResponse>> handleResourceNotFound() {
        ResourceNotFoundResponse response = new ResourceNotFoundResponse(System.currentTimeMillis());
        EntityModel<ResourceNotFoundResponse> responseEntityModel = EntityModel.of(response,
        linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
        );
        return new ResponseEntity<>(responseEntityModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityModel<UserNotFoundResponse>> handleAllExceptions(Exception e) {
        UserNotFoundResponse u = new UserNotFoundResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        EntityModel<UserNotFoundResponse> uModel = EntityModel.of(u,
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(uModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityModel<UserExistsResponse>> handleUserExistsException(UserExistsException e) {
        UserExistsResponse u = new UserExistsResponse(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
        EntityModel<UserExistsResponse> uModel = EntityModel.of(u,
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(uModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<EntityModel<IllegalOperationResponse>> handleIllegalOperationException(IllegalOperationException e) {
        IllegalOperationResponse res = new IllegalOperationResponse(e.getStatus(), e.getMessage(), System.currentTimeMillis());
        EntityModel<IllegalOperationResponse> resModel = EntityModel.of(res,
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(resModel, HttpStatus.valueOf(e.getStatus()));
    }
}
