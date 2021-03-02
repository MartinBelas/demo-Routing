package com.example.calculateRouteDemo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoutingServiceTest {
    
    @Autowired
    RoutingService serviceUnderTest;
    
    @BeforeEach
    public void setup(){
    }
    
    @Test
    void testShortWay_CZE_ITA() throws NoWayException {
        
        String from = "CZE";
        String to = "ITA";
        
        String[] expected = {"CZE, AUT, ITA"}; 
        
        String[] result = serviceUnderTest.getRouteWithLeastBorders(from, to);
        
        assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }
    
    @Test
    void testShortWay_DEU_ESP() throws NoWayException {
        
        String from = "DEU";
        String to = "ESP";
        
        int expectedLength = 3; 
        
        String[] result = serviceUnderTest.getRouteWithLeastBorders(from, to);
        
        assertEquals(expectedLength, result.length);
    }
    
    @Test
    void testLongerWay_CZE_SWE() throws NoWayException {
        
        String from = "CZE";
        String to = "SWE";
        
        int expectedLength = 5; 
        
        String[] result = serviceUnderTest.getRouteWithLeastBorders(from, to);
        
        assertEquals(expectedLength, result.length);
    }
    
    @Test
    void testLongWay_ESP_NPL() throws NoWayException {
        
        String from = "ESP";
        String to = "NPL";
        
        String[] expected = {"ESP, FRA, DEU, POL, RUS, CHN, NPL"};
        
        String[] result = serviceUnderTest.getRouteWithLeastBorders(from, to);
        
        assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }
    
    @Test
    void testLongWay_USA_ARG() throws NoWayException {
        
        String from = "USA";
        String to = "ARG";
        
        int expectedLength = 10; 
        
        String[] result = serviceUnderTest.getRouteWithLeastBorders(from, to);
        
        System.out.println(" LONFER --> " + Arrays.toString(result));
        assertEquals(expectedLength, result.length);
    }
    
    @Test
    void testNonExistingWay_USA_RUS() throws NoWayException {
        
        String from = "USA";
        String to = "RUS";
        
        Assertions.assertThrows(NoWayException.class, () -> {
            String[] result = serviceUnderTest.getRouteWithLeastBorders(from, to);
        });
    }
}
