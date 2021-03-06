package com.epam.movies.configuration;

import com.epam.movies.filter.CORSFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

@SpringBootApplication
public class MovieTheaterInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

    @Override
    protected Filter[] getServletFilters() {

        return new Filter[]{new CORSFilter(), new CharacterEncodingFilter("UTF-8")};
    }

}