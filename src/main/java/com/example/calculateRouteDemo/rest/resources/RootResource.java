package com.example.calculateRouteDemo.rest.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root Resource for test purposes.
 */
@Path("/")
@Controller
public class RootResource {

    private static final Logger log = LogManager.getLogger(RootResource.class);
    
    @Value("${app.user}")
    private String user;

    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public String getHello() {
        log.info("Hello world {} ", user);
        return String.format("Hello world %s", user);
    }
}