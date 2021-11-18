package com.bcf.ero.countryroutes.service;

import com.bcf.ero.countryroutes.model.Country;

import java.util.Map;

public interface CountryService {

    /**
     * Generates a hashmap of country name as key and country object
     * with border information as value
     *
     * @return Map
     * */
    Map<String, Country> getCountries();
}
