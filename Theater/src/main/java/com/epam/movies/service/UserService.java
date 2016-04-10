package com.epam.movies.service;

import com.epam.movies.model.User;

import java.util.List;


public interface UserService {

    User register(User user);


    boolean remove(long id);


    User getById(long id);


    User getUserByEmail(String email);

    List<User> getAll();


    boolean isUserExist(User user);


    void update(User user);


}
