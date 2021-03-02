package com.example.calculateRouteDemo.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.calculateRouteDemo.dao.CountryDao;
import com.example.calculateRouteDemo.domain.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoutingService {
    
    private static final Logger log = LogManager.getLogger(RoutingService.class);
    
    private static int MAX_BORDERS_COUNT;
    
    @Autowired
    private CountryDao dao;
    
    private Map<String, CountryWithRoute> unprocessedCountries;
    private Map<String, CountryWithRoute> processedCountriesRoutes;
    
    /**
     *  Calculates the path with the least borders crossings between two countries.
     *
     * @param start country
     * @param target country
     * @return count of borders
     * @throws NoWayException 
     */
    public String[] getRouteWithLeastBorders(String from, String to) throws NoWayException {
        
        MAX_BORDERS_COUNT = dao.getAll().size() - 1;
        
        this.unprocessedCountries = new HashMap<>();
        this.processedCountriesRoutes = new HashMap<>();
        
        Country start = dao.get(from);
        Country target = dao.get(to);
        
        if (start == null || target == null)
            throw new NoWayException("Start or target does not exist.");

        String[] result = null;

        this.processGraph(start);

        if (this.processedCountriesRoutes.containsKey(target.getId())) {
            result = this.processedCountriesRoutes.get(target.getId()).getRoute();
        }
        
        if (result == null)
            throw new NoWayException(String.format("No land way from %s to %s.", from, to));

        return result;
    }
    
    private class CountryWithRoute {
        
        private Country country;
        private List<String> routeFromStart = new ArrayList<>();
        
        CountryWithRoute (Country country) {
            this.country = country;
        }
        
        public void addBorder(String countryId) {
            routeFromStart.add(countryId);
        }
        
        void updateRoute(List<String> route) {
            this.routeFromStart.clear();
            route.stream().forEach(this.routeFromStart::add);
        }
        
        Country getCountry() {
            return this.country;
        }
        
        int getRouteSize() {
            return this.routeFromStart.size();
        }
        
        String[] getRoute() {
            return routeFromStart.toArray(new String[0]);
        }

    }
    
    private void processGraph(Country startCountry) {

        CountryWithRoute start = new CountryWithRoute(startCountry);
        start.addBorder(startCountry.getId());
        
        this.unprocessedCountries.put(
                startCountry.getId(), 
                start);

        while (this.unprocessedCountries.size() > 0) {
            
            CountryWithRoute current = getClosestCountry(this.unprocessedCountries);
            
            if (current != null) {
                
                this.unprocessedCountries.remove(current.getCountry().getId());
                
                List<String> currentRoute = Arrays.asList(current.getRoute());
                this.processedCountriesRoutes.put(current.getCountry().getId(), current);
                
                for (String neighborId : current.getCountry().getBorders()) {
                    
                    if (!this.unprocessedCountries.containsKey(neighborId)) {
                        
                        Country neighbor = dao.get(neighborId);
                        CountryWithRoute neighborWithRoute = new CountryWithRoute(neighbor);
                        neighborWithRoute.updateRoute(currentRoute);
                        neighborWithRoute.addBorder(neighborId);
                        
                        if (!this.processedCountriesRoutes.containsKey(neighborId)) {
                            calculateMinimumDistance(neighborWithRoute, current);
                            this.unprocessedCountries.put(neighborId, neighborWithRoute);
                        }
                    }
                }
            }
        }
    }
    
    private void calculateMinimumDistance(CountryWithRoute neighbor, CountryWithRoute current) {

        Integer currentDistance = current.getRouteSize();
        
        if (currentDistance + 1 < neighbor.getRouteSize()) {
            
            ArrayList<String> shortestPath = new ArrayList<>(Arrays.asList(current.getRoute()));
            shortestPath.add(neighbor.getCountry().getId());
            neighbor.updateRoute(shortestPath);
        }
    }
    
    private CountryWithRoute getClosestCountry(Map<String, CountryWithRoute> unprocessed) {

        CountryWithRoute closestCountry = null;
        int lowestDistance = MAX_BORDERS_COUNT;

        int routeLength;

        for (CountryWithRoute countryWithRoute : unprocessed.values()) {
            routeLength = countryWithRoute.getRouteSize();
            
            if (closestCountry == null) {
                closestCountry = countryWithRoute;
                lowestDistance = closestCountry.getRouteSize(); 
            } else if (routeLength < lowestDistance) {
                lowestDistance = routeLength;
                closestCountry = countryWithRoute;
            }
        }

        return closestCountry;
    }
}
