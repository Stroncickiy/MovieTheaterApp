package com.epam.movietheater;

import com.epam.movietheater.generated.users.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class UserServiceClient extends WebServiceGatewaySupport {
    private ObjectFactory objectFactory = new ObjectFactory();

    public User registerUser(User user) {
        RegisterUserRequest register = new RegisterUserRequest();
        register.setUser(user);
        JAXBElement<RegisterUserRequest> registerJAXBElement = objectFactory.createRegisterUserRequest(register);
        JAXBElement<RegisterUserResponse> response = (JAXBElement<RegisterUserResponse>) getWebServiceTemplate()
                .marshalSendAndReceive(registerJAXBElement);
        return response.getValue().getCreatedUser();
    }

    public boolean removeUser(Long userId) {
        RemoveUserRequest removeRequest = new RemoveUserRequest();
        removeRequest.setId(userId);
        JAXBElement<RemoveUserRequest> removeRequestJAXBElement = objectFactory.createRemoveUserRequest(removeRequest);
        JAXBElement<RemoveUserResponse> removeResponseJAXBElement = (JAXBElement<RemoveUserResponse>) getWebServiceTemplate().marshalSendAndReceive(removeRequestJAXBElement);
        return removeResponseJAXBElement.getValue().isRemoved();
    }

    public User getById(Long id) {
        GetUserByIdRequest getByIdRequest = new GetUserByIdRequest();
        getByIdRequest.setId(id);
        JAXBElement<GetUserByIdRequest> getByIdRequestJAXBElement = objectFactory.createGetUserByIdRequest(getByIdRequest);
        JAXBElement<GetUserByIdResponse> getByIdResponseJAXBElement = (JAXBElement<GetUserByIdResponse>) getWebServiceTemplate().marshalSendAndReceive(getByIdRequestJAXBElement);
        return getByIdResponseJAXBElement.getValue().getUser();
    }

    public User getByEmail(String email) {
        GetUserByEmailRequest getByEmailRequest = new GetUserByEmailRequest();
        getByEmailRequest.setEmail(email);
        JAXBElement<GetUserByEmailRequest> getByEmailRequestJAXBElement = objectFactory.createGetUserByEmailRequest(getByEmailRequest);
        JAXBElement<GetUserByEmailResponse> getByEmailResponseJAXBElement = (JAXBElement<GetUserByEmailResponse>) getWebServiceTemplate().marshalSendAndReceive(getByEmailRequestJAXBElement);
        return getByEmailResponseJAXBElement.getValue().getUser();
    }

}
