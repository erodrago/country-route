package com.bcf.ero.countryroutes.service.impl;

import com.bcf.ero.countryroutes.model.Country;
import com.bcf.ero.countryroutes.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    @Value("${data.url}")
    private String url;

    private RestTemplate restTemplate;

    @Override
    public Map<String, Country> getCountries() {
        ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(url, GET, null,
                new ParameterizedTypeReference<>() {});
        List<Country> countries = responseEntity.getBody();

        return countries
                .stream()
                .collect(Collectors.toMap(Country::getName, country -> country));
    }
}
