package com.epam.movies.ws.services;

import com.epam.movies.enums.UserRole;
import com.epam.movies.model.User;
import com.epam.movies.service.UserService;
import com.epam.movies.ws.generated.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.time.LocalDate;

@Endpoint
public class UsersEndpoint {
    private static final String NAMESPACE_URI = "http://movietheater.epam.com";

    @Autowired
    private UserService userService;

    private ObjectFactory objectFactory = new ObjectFactory();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeUserRequest")
    @ResponsePayload
    public JAXBElement<RemoveUserResponse> removeUser(@RequestPayload JAXBElement<RemoveUserRequest> removeRequest) {
        boolean removed = userService.remove(removeRequest.getValue().getId());
        RemoveUserResponse removeUserResponse = new RemoveUserResponse();
        removeUserResponse.setRemoved(removed);
        return objectFactory.createRemoveUserResponse(removeUserResponse);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerUserRequest")
    @ResponsePayload
    public JAXBElement<RegisterUserResponse> registerUser(@RequestPayload JAXBElement<RegisterUserRequest> register) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        User userFromRequest = register.getValue().getUser();
        userFromRequest.setBirthDate(LocalDate.of(1996, 8, 25));
        if (!userFromRequest.getRoles().contains(UserRole.REGISTERED_USER)) {
            userFromRequest.getRoles().add(UserRole.REGISTERED_USER);
        }
        registerUserResponse.setCreatedUser(userService.register(userFromRequest));
        return objectFactory.createRegisterUserResponse(registerUserResponse);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public JAXBElement<GetUserByIdResponse> getById(@RequestPayload JAXBElement<GetUserByIdRequest> getByIdRequestJAXBElement) {
        GetUserByIdResponse getUserByIdResponse = new GetUserByIdResponse();
        getUserByIdResponse.setUser(userService.getById(getByIdRequestJAXBElement.getValue().getId()));
        return objectFactory.createGetUserByIdResponse(getUserByIdResponse);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByEmailRequest")
    @ResponsePayload
    public JAXBElement<GetUserByEmailResponse> getByEmail(@RequestPayload JAXBElement<GetUserByEmailRequest> getUserByEmailRequestJAXBElement) {
        GetUserByEmailResponse getUserByEmailResponse = new GetUserByEmailResponse();
        getUserByEmailResponse.setUser(userService.getUserByEmail(getUserByEmailRequestJAXBElement.getValue().getEmail()));
        return objectFactory.createGetUserByEmailResponse(getUserByEmailResponse);
    }


}
