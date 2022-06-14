package com.example.demo.serivce;

import com.example.demo.model.Status;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    Status saveAndLogin(User user);

    Status signUp(User user);

    void save(User user);

    List<User> findAll();
}
