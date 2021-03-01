package com.example.calculateRouteDemo;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.calculateRouteDemo.rest.resources.RootResource;
import com.example.calculateRouteDemo.rest.resources.RoutingResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RootResource.class);
        register(RoutingResource.class);
    }
}

