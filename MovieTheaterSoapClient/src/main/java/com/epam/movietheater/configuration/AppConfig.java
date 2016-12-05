package com.epam.movietheater.configuration;

import com.epam.movietheater.EventServiceClient;
import com.epam.movietheater.UserServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class AppConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths("com.epam.movietheater.generated.users", "com.epam.movietheater.generated.events");
        return marshaller;
    }

    @Bean
    public UserServiceClient userServiceClient(Jaxb2Marshaller marshaller) {
        UserServiceClient client = new UserServiceClient();
        client.setDefaultUri("http://localhost:8080/ws/userService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public EventServiceClient eventServiceClient(Jaxb2Marshaller marshaller) {
        EventServiceClient client = new EventServiceClient();
        client.setDefaultUri("http://localhost:8080/ws/eventService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
