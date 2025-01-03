package com.ssaisriharsha.RestApi.DBOps;

import java.util.List;

public interface UserDAO {
    User save(User user);
    User findById(int id);
    List<User> findByLastName(String lastName);
    List<User> findByFirstName(String firstName);
    List<User> findByActivityStatus(Boolean activityStatus);
    List<User> findAll();
//    int updateUserById(User user);
    int deleteUserById(int id);
    User findByEmail(String mailId);
}
