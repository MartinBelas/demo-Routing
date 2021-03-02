package com.example.calculateRouteDemo.services;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.calculateRouteDemo.dao.CountryDao;
import com.example.calculateRouteDemo.domain.Country;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
public class CountryJsonDao implements CountryDao {

    private static final Logger log = LogManager.getLogger(CountryJsonDao.class);
    
    private static Map<String, Country> countries = new HashMap<>();
    
    CountryJsonDao(@Value("${app.countriesjson}") String countriesFilePath) {
        
        if (countries.isEmpty()) {
            try {
                readCountriesFromFile(countriesFilePath);
            } catch (IOException e) {
                String errMsg = String.format("Can't read from file %s ", countriesFilePath);
                log.error(errMsg);
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    @Override
    public Map<String, Country> getAll() {
        
        Map<String, Country> copy = new HashMap<>();

        countries.forEach( (id, country) -> {
            copy.put(id, country);
        });

        return copy;
    }
    
    @Override
    public Country get(String id) {
        return countries.get(id);
    }
    
    private static void readCountriesFromFile(String countriesFilePath) throws IOException {
        
        log.info("Reading countries from input json file {} ...", countriesFilePath);
        
        ObjectMapper mapper = new ObjectMapper();

        Country[] countriesArray;

        countriesArray = mapper.readValue(new File(countriesFilePath), Country[].class);
        countries = Arrays.asList(countriesArray)
                .stream()
                .collect(Collectors.toMap(Country::getId, c -> c));
        
        log.info("{} countries have been read", countries.size());
    }
}
