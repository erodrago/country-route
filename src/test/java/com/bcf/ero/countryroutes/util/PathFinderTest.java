package com.bcf.ero.countryroutes.util;

import com.bcf.ero.countryroutes.exception.PathNotFoundException;
import com.bcf.ero.countryroutes.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    private Country countryA, countryB, countryC, countryD, countryE, countryF;
    private PathFinder pathFinder;
    private Map<String, Country> countriesMap;


    @BeforeEach
    void setUp() {
        countryA = new Country("KEN", Arrays.asList("UGA", "TAZ", "ETH", "SOM", "RWA"));
        countryB = new Country("UGA", Arrays.asList("KEN", "ETH"));
        countryD = new Country("TAZ", Arrays.asList("UGA", "KEN"));
        countryC = new Country("RWA", Arrays.asList("KEN", "UGA", "TAZ"));
        countryE = new Country("SOM", Collections.singletonList("ETH"));
        countryF = new Country("ETH", Collections.emptyList());

        countriesMap = new HashMap<>();
        countriesMap.put(countryA.getName(), countryA);
        countriesMap.put(countryB.getName(), countryB);
        countriesMap.put(countryC.getName(), countryC);
        countriesMap.put(countryD.getName(), countryD);
        countriesMap.put(countryE.getName(), countryE);
    }

    @Test
    void findPath_ShouldThrowException() {

        pathFinder = new PathFinder(countriesMap, countryF, countryB);

        Assertions.assertThrows(PathNotFoundException.class, () -> pathFinder.findPath().size());

    }

    @Test
    void findPath() throws PathNotFoundException {

        pathFinder = new PathFinder(countriesMap, countryA, countryD);

        assertEquals(pathFinder.findPath().size(), 2);

    }
}