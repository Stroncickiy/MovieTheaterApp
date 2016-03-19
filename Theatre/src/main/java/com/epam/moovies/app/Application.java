package com.epam.moovies.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.epam.moovies.configuration.MovieTheatreInitializer;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.epam.moovies")
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).child(MovieTheatreInitializer.class).run(args);
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		return factory;
	}
}
