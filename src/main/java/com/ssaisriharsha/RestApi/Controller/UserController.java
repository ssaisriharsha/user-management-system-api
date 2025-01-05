package com.ssaisriharsha.RestApi.Controller;

import com.ssaisriharsha.RestApi.DBOps.User;
import com.ssaisriharsha.RestApi.Exceptions.IllegalOperationException;
import com.ssaisriharsha.RestApi.Exceptions.UserExistsException;
import com.ssaisriharsha.RestApi.Exceptions.UserNotFoundException;
import com.ssaisriharsha.RestApi.Service.DeleteResponse;
import com.ssaisriharsha.RestApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<EntityModel<User>> userModels = users.stream().map(
                (user) -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"),
                        linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel()
                )
        ).collect(Collectors.toList());
        CollectionModel<EntityModel<User>> userCollection = CollectionModel.of(userModels,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel()
                );
        return new ResponseEntity<>(userCollection, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user==null) {
            throw new UserNotFoundException("User not found.");
        }
        EntityModel<User> userModel = EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
        );
        return new ResponseEntity<>(userModel, HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<EntityModel<DeleteResponse>> deleteUser(@PathVariable("id") int id) {
        int rowsAffected = userService.deleteUser(id);
        if(rowsAffected==0) {
            throw new UserNotFoundException("Requested user isn't available in the database.");
        }
        DeleteResponse res = new DeleteResponse("Requested resource deleted successfully.", HttpStatus.OK.value());
        EntityModel<DeleteResponse> deleteModel = EntityModel.of(res,
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(deleteModel, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(user.getId()!=0) {
            user.setId(0);
        }
        if(userService.getUserByEmail(user.getEmail())!=null){
            throw new UserExistsException("The given mail id is already associated with another user");
        }
        User u = userService.addUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        if(user.getId()!=id) {
            throw new IllegalOperationException("The endpoint's ID must match the entity's ID", HttpStatus.BAD_REQUEST.value());
        }
        if(user.getId()==0) {
            throw new IllegalOperationException("Cannot create an entity using this endpoint.", HttpStatus.FORBIDDEN.value());
        }
        if(userService.getUserById(user.getId())==null) {
            throw new IllegalOperationException("Cannot find an entity with the given ID", HttpStatus.NOT_FOUND.value());
        }
        User u = userService.updateUser(user);
        EntityModel<User> userModel = EntityModel.of(u,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
        return new ResponseEntity<>(userModel, HttpStatus.ACCEPTED);
    }
}
