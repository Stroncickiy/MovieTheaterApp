package com.epam.movies.controller;

import com.epam.movies.enums.UserRole;
import com.epam.movies.model.User;
import com.epam.movies.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class AuthContorller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        passwordEncoder = new BCryptPasswordEncoder();
    }


    @Autowired
    private UserService userService;

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
        return "/loging";
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("newUser") User user) {
        if (userService.isUserExist(user)) {
            return "redirect:/register";
        }
        List<UserRole> roles = user.getRoles();
        roles.add(UserRole.REGISTERED_USER);
        user.setRoles(roles);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.register(user);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redidect:/";
    }
}
