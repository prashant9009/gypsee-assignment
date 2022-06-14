package com.example.demo.serivce.impl;

import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Status saveAndLogin(User user) {
        List<User> users = findAll();
        for (User u : users) {
            if (user.getEmail().equals(u.getEmail())) {
                return Status.ALREADY_EXISTS_AND_LOGGED_IN;
            }
            if (u.getActive()) {
                u.setActive(false);
                userRepository.save(u);
                break;
            }
        }
        user.setActive(true);
        userRepository.save(user);
        return Status.USER_CREATED_AND_LOGGED_IN;
    }

    @Override
    public Status signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Status.ALREADY_EXISTS;
        }
        user.setActive(false);
        userRepository.save(user);
        return Status.USER_CREATED;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
