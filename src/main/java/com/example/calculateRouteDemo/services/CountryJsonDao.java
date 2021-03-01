package com.example.calculateRouteDemo.services;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.calculateRouteDemo.AppConfig;
import com.example.calculateRouteDemo.dao.CountryDao;
import com.example.calculateRouteDemo.domain.Country;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
public class CountryJsonDao implements CountryDao {

    private static final Logger log = LogManager.getLogger(CountryJsonDao.class);
    
    private static Map<String, Country> countries = new HashMap<>();
    
    AppConfig cfg;

    @Autowired
    private CountryJsonDao(AppConfig cfg) {
        this.cfg = cfg;
        if (countries.isEmpty()) {
            try {
                readCountriesFromFile(cfg.getCountriesJson());
            } catch (IOException e) {
                String errMsg = String.format("Can't read from file %s ", cfg.getCountriesJson());
                log.error(errMsg);
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    @Override
    public Map<String, Country> getAll() {
        // TODO Maybe should return a deep copy ?? 
        return countries;
    }
    
    private static void readCountriesFromFile(String filePath) throws JsonParseException, JsonMappingException, IOException {
        
        log.info("Reading countries from input json file {} ...", filePath);
        
        ObjectMapper mapper = new ObjectMapper();

        Country[] countriesArray = mapper.readValue(new File(filePath), Country[].class);
        
        countries = Arrays.asList(countriesArray)
            .stream()
            .collect(Collectors.toMap(Country::getId, c -> c));
        
        log.info("{} countries have been read", countries.size());
    }
}
