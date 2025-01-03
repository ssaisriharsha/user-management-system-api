package com.ssaisriharsha.RestApi.Controller;

import com.ssaisriharsha.RestApi.DBOps.User;
import com.ssaisriharsha.RestApi.Exceptions.IllegalOperationException;
import com.ssaisriharsha.RestApi.Exceptions.UserExistsException;
import com.ssaisriharsha.RestApi.Exceptions.UserNotFoundException;
import com.ssaisriharsha.RestApi.Service.DeleteResponse;
import com.ssaisriharsha.RestApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user==null) {
            throw new UserNotFoundException("User not found.");
        }
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable("id") int id) {
        int rowsAffected = userService.deleteUser(id);
        if(rowsAffected==0) {
            throw new UserNotFoundException("Requested user isn't available in the database.");
        }
        DeleteResponse res = new DeleteResponse("Requested resource deleted successfully.", HttpStatus.OK.value());
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if(user.getId()==0) {
            throw new IllegalOperationException("Cannot create an entity using this endpoint.", HttpStatus.FORBIDDEN.value());
        }
        if(userService.getUserById(user.getId())==null) {
            throw new IllegalOperationException("Cannot find an entity with the given ID", HttpStatus.NOT_FOUND.value());
        }
        User u = userService.updateUser(user);
        return new ResponseEntity<>(u, HttpStatus.ACCEPTED);
    }
}
