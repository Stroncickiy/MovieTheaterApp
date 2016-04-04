package com.epam.movies.service;

import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;

public interface UserAccountService {
    UserAccount getForUser(User user);

    UserAccount register(UserAccount account);

    void update(UserAccount account);

    void delete(Long id);

}
