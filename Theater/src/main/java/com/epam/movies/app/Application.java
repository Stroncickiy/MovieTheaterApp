package com.epam.movies.app;

import com.epam.movies.configuration.MovieTheaterInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;


public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MovieTheaterInitializer.class).run(args);
    }

}
