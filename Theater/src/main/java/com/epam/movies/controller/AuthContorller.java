package com.epam.movies.controller;

import com.epam.movies.model.User;
import com.epam.movies.model.UserRole;
import com.epam.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.Date;

public class AuthContorller {

    @PostConstruct
    public void init() {
        passwordEncoder = new BCryptPasswordEncoder();
    }


    @Autowired
    private UserService userService;  //Service which will do all data retrieval/manipulation work

    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userService.isUserExist(user)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UserRole userRole = new UserRole();
        userRole.setRole("CUSTOMER");
        user.setUserRoles(null); // TODO get userRoles from request
        user.setBirthDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.register(user);

        if (userService.isUserExist(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
