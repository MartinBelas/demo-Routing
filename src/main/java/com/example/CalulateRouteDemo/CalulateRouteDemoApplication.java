package com.example.CalulateRouteDemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CalulateRouteDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new CalulateRouteDemoApplication()
                .configure(new SpringApplicationBuilder(CalulateRouteDemoApplication.class))
                .run(args);
    }
}