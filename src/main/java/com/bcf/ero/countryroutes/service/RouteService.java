package com.bcf.ero.countryroutes.service;

import com.bcf.ero.countryroutes.exception.PathNotFoundException;
import com.bcf.ero.countryroutes.model.Route;

public interface RouteService {
    /**
     * Retrieves route from origin to destination
     *
     * @param origin
     * @param destination
     *
     * @return Route
     */
    Route findRoute(String origin, String destination) throws PathNotFoundException;
}
