package com.example.CalulateRouteDemo.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoutingService {
    public String[] getRoute(String from, String to) {

        List<String> route = new ArrayList<>();

        //TODO impl. alg.

        route.add("CZE");
        route.add("AUT");
        route.add("ITA");

        return route.toArray(new String[0]);
    }
}
