package com.example.calculateRouteDemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties(prefix="app")
public class AppConfig {

    private String user;
    public void setUser(String user) { this.user = user;}
    public String getUser() { return this.user; }
    
    private String countriesJson;
    public void setCountriesJson(String countriesjson) { this.countriesJson = countriesjson;}
    public String getCountriesJson() { return this.countriesJson; }
}