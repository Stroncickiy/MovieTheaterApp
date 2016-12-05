package com.movietheater.rest.test;

import com.epam.movies.enums.UserRole;
import com.epam.movies.model.User;
import com.epam.movietheater.configuration.AppConfig;
import com.google.common.collect.Lists;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRESTServiceTest {

    private RestTemplate restTemplate = new RestTemplate();

    private static User createdUser;

    @Test
    public void test1RegisterUser() {
        User user = new User();
        user.setId(10L);
        user.setFirstName("Slavik");
        user.setEmail("slavik@gmail.com");
        user.setBirthDate(LocalDate.now());
        user.setPassword("password");
        user.setEnabled(true);
        user.setLastName("Strontsitskyy");
        user.setPassword("ivasda");
        user.setRoles(new ArrayList<>());
        user.getRoles().add(UserRole.BOOKING_MANAGER);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<User> userResponseEntity = restTemplate.exchange("http://localhost:8080/rest/user", HttpMethod.POST, entity, User.class);
        User registeredUser = userResponseEntity.getBody();
        createdUser = registeredUser;
        System.out.println("\nUser Service Client Test ");
        System.out.println("---------------------------- Test 1 ");
        System.out.println("User " + registeredUser.getFirstName() + " " + registeredUser.getLastName() +
                " was successfully registered with id " + registeredUser.getId());
        System.out.println("-------------------------------------");

    }

    @Test
    public void test2GetById() {
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("http://localhost:8080/rest/user/{userId}", User.class, createdUser.getId());
        User byId = userResponseEntity.getBody();
        System.out.println("---------------------------- Test 2 ");
        System.out.println("User " + byId.getFirstName() + " " + byId.getLastName() +
                " was successfully fetched  by id " + byId.getId());
        System.out.println("-------------------------------------");
    }

    @Test
    public void test4RemoveCreatedUser() {
        restTemplate.delete("http://localhost:8080/rest/user/{userId}", createdUser.getId());
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("http://localhost:8080/rest/user/{userId}", User.class, createdUser.getId());
        System.out.println("---------------------------- Test 4 ");
        if (userResponseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
            System.out.println("user " + createdUser.getId() + " was removed ");
        } else {
            System.out.println("user " + createdUser.getId() + " was not  removed ");
        }
        System.out.println("---------------------------- Complete");
    }
}
