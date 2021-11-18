package com.bcf.ero.countryroutes.service.impl;

import com.bcf.ero.countryroutes.exception.PathNotFoundException;
import com.bcf.ero.countryroutes.model.Country;
import com.bcf.ero.countryroutes.model.Route;
import com.bcf.ero.countryroutes.service.RouteService;
import com.bcf.ero.countryroutes.util.PathFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteServiceImpl implements RouteService {

    private final CountryServiceImpl countryService;

    @Override
    public Route findRoute(String origin, String destination) throws PathNotFoundException {
        Map<String, Country> countries = countryService.getCountries();

        if(countries.get(origin) == null || countries.get(destination) == null) {
            throw new PathNotFoundException("Path does not exist");
        }
        PathFinder pathFinder = new PathFinder(countries, countries.get(origin), countries.get(destination));

        List<String> path = pathFinder.findPath();

        return new Route(path);
    }
}
