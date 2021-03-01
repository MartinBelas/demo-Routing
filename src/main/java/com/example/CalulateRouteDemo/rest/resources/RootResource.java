package com.example.CalulateRouteDemo.rest.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root Resource for test purposes.
 */
@Path("/")
public class RootResource {

    private static final Logger log = LogManager.getLogger(RootResource.class);

    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public String getHello() {

        log.info("Hello world...");

        return "Hello world";
    }
}