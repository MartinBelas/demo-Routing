package com.example.calculateRouteDemo.dao;

import java.util.Map;

import com.example.calculateRouteDemo.domain.Country;

public interface CountryDao {

    Map<String, Country> getAll();

    Country get(String from);
}
