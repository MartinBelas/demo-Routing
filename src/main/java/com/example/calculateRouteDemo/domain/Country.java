package com.example.calculateRouteDemo.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    
    @JsonProperty("cca3")
    private String id;
    
    // list of neighbors
    @JsonProperty("borders")
    private Set<String> borders;

    public String getId() {
        return this.id;
    }
    
    public Set<String> getBorders() {
        return this.borders;
    }
}
