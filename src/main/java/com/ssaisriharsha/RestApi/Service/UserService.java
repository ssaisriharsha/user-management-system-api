package com.ssaisriharsha.RestApi.Service;

import com.ssaisriharsha.RestApi.DBOps.User;
import com.ssaisriharsha.RestApi.DBOps.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements ServiceInterface {
    private final UserDAO userRepo;

    @Autowired
    public UserService(UserDAO userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public User addUser(User user) {
        @SuppressWarnings("")
        User u = userRepo.save(user);
        return u;
    }

    @Transactional
    @Override
    public int deleteUser(int id) {
        @SuppressWarnings("")
        int rowsAffected = userRepo.deleteUserById(id);
        return rowsAffected;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        @SuppressWarnings("")
        User u = userRepo.save(user);
        return u;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id);
    }

    public User getUserByEmail(String mailId) {
        return userRepo.findByEmail(mailId);
    }
}
