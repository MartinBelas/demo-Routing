package com.example.CalulateRouteDemo.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/routing")
public class RoutingResource {

    private static final Logger log = LogManager.getLogger(RoutingResource.class);

    @Path("/{from}/{to}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRoute(
            @PathParam("from") String from,
            @PathParam("to") String to
            ) {

        log.info(" Find route from {} to {} ...", from, to);

        //TODO
        return null;
    }
}