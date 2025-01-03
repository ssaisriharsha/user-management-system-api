package com.ssaisriharsha.RestApi.Service;

import com.ssaisriharsha.RestApi.DBOps.User;

import java.util.List;

public interface ServiceInterface {
    List<User> getAllUsers();
    User getUserById(int id);
    User addUser(User user);
    User updateUser(User user);
    int deleteUser(int id);
}
