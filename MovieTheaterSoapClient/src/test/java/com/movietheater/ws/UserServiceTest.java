package com.movietheater.ws;


import com.epam.movietheater.UserServiceClient;
import com.epam.movietheater.configuration.AppConfig;
import com.epam.movietheater.generated.users.User;
import com.epam.movietheater.generated.users.UserRole;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserServiceClient userServiceClient;

    private static User createdUser;

    @Test
    public void test1RegisterUser() {
        User user = new User();
        user.setFirstName("Slavik");
        user.setEmail("slavik@gmail.com");
        user.setEnabled(true);
        user.setLastName("Strontsitskyy");
        user.setPassword("ivasda");
        user.getRoles().add(UserRole.BOOKING_MANAGER);
        User registeredUser = userServiceClient.registerUser(user);
        createdUser = registeredUser;
        System.out.println("\nUser Service Client Test ");
        System.out.println("---------------------------- Test 1 ");
        System.out.println("User " + registeredUser.getFirstName() + " " + registeredUser.getLastName() +
                " was successfully registered with id " + registeredUser.getId());
        System.out.println("-------------------------------------");

    }

    @Test
    public void test2GetById() {
        User byId = userServiceClient.getById(createdUser.getId());
        System.out.println("---------------------------- Test 2 ");
        System.out.println("User " + byId.getFirstName() + " " + byId.getLastName() +
                " was successfully fetched  by id " + byId.getId());
        System.out.println("-------------------------------------");
    }

    @Test
    public void test3GetByEmail() {
        User byEmail = userServiceClient.getByEmail(createdUser.getEmail());
        System.out.println("---------------------------- Test 3 ");
        System.out.println("User " + byEmail.getFirstName() + " " + byEmail.getLastName() +
                " was successfully fetched  by email " + byEmail.getId());
        System.out.println("-------------------------------------");
    }


    @Test
    public void test4RemoveCreatedUser() {
        boolean removed = userServiceClient.removeUser(createdUser.getId());
        System.out.println("---------------------------- Test 4 ");
        if (removed) {
            System.out.println("user " + createdUser.getId() + " was removed ");
        } else {
            System.out.println("user " + createdUser.getId() + " was not  removed ");
        }
        System.out.println("---------------------------- Complete");
    }
}
