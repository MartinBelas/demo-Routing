package com.example.CalulateRouteDemo;


import com.example.CalulateRouteDemo.rest.resources.RootResource;
import com.example.CalulateRouteDemo.rest.resources.RoutingResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RootResource.class);
        register(RoutingResource.class);
    }
}

