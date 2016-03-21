package com.epam.moovies.app;

import org.springframework.boot.builder.SpringApplicationBuilder;

import com.epam.moovies.configuration.ApplicationConfig;
import com.epam.moovies.configuration.MovieTheatreInitializer;


public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationConfig.class).child(MovieTheatreInitializer.class).run(args);
	}

}
