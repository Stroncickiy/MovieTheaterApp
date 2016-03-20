package com.epam.moovies.app;

import com.epam.moovies.configuration.ApplicationConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.epam.moovies.configuration.MovieTheatreInitializer;


public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationConfig.class).child(MovieTheatreInitializer.class).run(args);
	}

}
