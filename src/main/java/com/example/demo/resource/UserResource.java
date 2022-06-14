package com.example.demo.resource;

import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    ResponseEntity<Status> createUser(@RequestBody User user) {
        Status response = userService.signUp(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup-and-login")
    ResponseEntity<Status> createUserAndLogin(@RequestBody User user) {
        Status response = userService.saveAndLogin(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    ResponseEntity<Status> login(@RequestBody User user) {
        List<User> users = userService.findAll();
        for (User u : users) {
            if (u.getActive()) {
                u.setActive(false);
                userService.save(u);
                break;
            }
        }
        for (User u : users) {
            if (user.getEmail().equals(u.getEmail())) {
                u.setActive(true);
                userService.save(u);
                return new ResponseEntity<>(Status.SIGNED_IN, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Status.NOT_SUCH_USER_EXISTS, HttpStatus.OK);
    }

    @PostMapping("/logout")
    ResponseEntity<Status> logOut(@RequestBody User user) {
        List<User> users = userService.findAll();
        for (User u : users) {
            if (user.getEmail().equals(u.getEmail()) && u.getActive()) {
                u.setActive(false);
                userService.save(u);
                return new ResponseEntity<>(Status.LOGGED_OUT, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Status.NOT_SUCH_USER_EXISTS, HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
