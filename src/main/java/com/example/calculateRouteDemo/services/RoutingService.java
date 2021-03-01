package com.example.calculateRouteDemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calculateRouteDemo.dao.CountryDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoutingService {
    
    @Autowired
    CountryDao dao;
    
    public String[] getRoute(String from, String to) {
        
        List<String> route = new ArrayList<>();

        //TODO impl. alg.

        route.add("CZE");
        route.add("AUT");
        route.add("ITA");

        return route.toArray(new String[0]);
    }
}
