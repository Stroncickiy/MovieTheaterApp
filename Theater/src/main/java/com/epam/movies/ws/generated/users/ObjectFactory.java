
package com.epam.movies.ws.generated.users;

import com.epam.movies.model.User;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.epam.movies.ws.generated.users package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUserByIdRequest_QNAME = new QName("http://movietheater.epam.com", "getUserByIdRequest");
    private final static QName _GetUserByEmailRequest_QNAME = new QName("http://movietheater.epam.com", "getUserByEmailRequest");
    private final static QName _RemoveUserRequest_QNAME = new QName("http://movietheater.epam.com", "removeUserRequest");
    private final static QName _User_QNAME = new QName("http://movietheater.epam.com", "user");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://movietheater.epam.com", "registerUserResponse");
    private final static QName _RegisterUserRequest_QNAME = new QName("http://movietheater.epam.com", "registerUserRequest");
    private final static QName _RemoveUserResponse_QNAME = new QName("http://movietheater.epam.com", "removeUserResponse");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://movietheater.epam.com", "getUserByIdResponse");
    private final static QName _GetUserByEmailResponse_QNAME = new QName("http://movietheater.epam.com", "getUserByEmailResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.movies.ws.generated.users
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterUserRequest }
     */
    public RegisterUserRequest createRegisterUserRequest() {
        return new RegisterUserRequest();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link GetUserByEmailResponse }
     */
    public GetUserByEmailResponse createGetUserByEmailResponse() {
        return new GetUserByEmailResponse();
    }

    /**
     * Create an instance of {@link GetUserByIdRequest }
     */
    public GetUserByIdRequest createGetUserByIdRequest() {
        return new GetUserByIdRequest();
    }

    /**
     * Create an instance of {@link GetUserByEmailRequest }
     */
    public GetUserByEmailRequest createGetUserByEmailRequest() {
        return new GetUserByEmailRequest();
    }

    /**
     * Create an instance of {@link RemoveUserRequest }
     */
    public RemoveUserRequest createRemoveUserRequest() {
        return new RemoveUserRequest();
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "getUserByIdRequest")
    public JAXBElement<GetUserByIdRequest> createGetUserByIdRequest(GetUserByIdRequest value) {
        return new JAXBElement<GetUserByIdRequest>(_GetUserByIdRequest_QNAME, GetUserByIdRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByEmailRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "getUserByEmailRequest")
    public JAXBElement<GetUserByEmailRequest> createGetUserByEmailRequest(GetUserByEmailRequest value) {
        return new JAXBElement<GetUserByEmailRequest>(_GetUserByEmailRequest_QNAME, GetUserByEmailRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "removeUserRequest")
    public JAXBElement<RemoveUserRequest> createRemoveUserRequest(RemoveUserRequest value) {
        return new JAXBElement<RemoveUserRequest>(_RemoveUserRequest_QNAME, RemoveUserRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "registerUserRequest")
    public JAXBElement<RegisterUserRequest> createRegisterUserRequest(RegisterUserRequest value) {
        return new JAXBElement<RegisterUserRequest>(_RegisterUserRequest_QNAME, RegisterUserRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByEmailResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "getUserByEmailResponse")
    public JAXBElement<GetUserByEmailResponse> createGetUserByEmailResponse(GetUserByEmailResponse value) {
        return new JAXBElement<GetUserByEmailResponse>(_GetUserByEmailResponse_QNAME, GetUserByEmailResponse.class, null, value);
    }

}
