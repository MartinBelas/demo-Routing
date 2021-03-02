package com.example.calculateRouteDemo.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.calculateRouteDemo.services.NoWayException;
import com.example.calculateRouteDemo.services.RoutingService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Path("/routing")
@Controller
public class RoutingResource {

    private static final Logger log = LogManager.getLogger(RoutingResource.class);
    
    @Value("${app.user}")
    private String user;

    @Autowired
    RoutingService service;

    @Path("/{from}/{to}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRoute(
            @PathParam("from") String from,
            @PathParam("to") String to
            ) {
        
        log.info(" Find route from {} to {} ...", from, to);

        String[] routes;
        try {
            routes = service.getRouteWithLeastBorders(from, to);
            JSONObject result = new JSONObject();
            result.put("route", routes);
            
            log.info(" Route from {} to {} : {}", from, to, result);
            return Response.status(Response.Status.OK).entity(result.toString()).build();
        } catch (NoWayException e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.toString()).build();
        }
        
    }
}