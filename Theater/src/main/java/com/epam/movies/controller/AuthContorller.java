package com.epam.movies.controller;

import com.epam.movies.enums.UserRole;
import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;
import com.epam.movies.service.UserAccountService;
import com.epam.movies.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthContorller {

    @PostConstruct
    public void init() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    private PasswordEncoder passwordEncoder;

    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String openRegisterPage(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("availableRoles", Lists.newArrayList(UserRole.BOOKING_MANAGER));
        return "signUp";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "/login";
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("newUser") User user) {
        if (userService.isUserExist(user)) {
            return "redirect:/register?userExist";
        }
        List<UserRole> roles = new ArrayList<>();
        if (user.getRoles() != null) {
            roles.addAll(user.getRoles());
        }
        roles.add(UserRole.REGISTERED_USER);
        user.setRoles(roles);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userService.register(user);
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(newUser.getId());
        userAccount.setBalance(1000L);
        userAccountService.register(userAccount);
        return "redirect:/";
    }

    @RequestMapping("/denied")
    public String accessDeniedPage() {
        return "denied";
    }
}
