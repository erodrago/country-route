package com.bcf.ero.countryroutes.controller;

import com.bcf.ero.countryroutes.exception.PathNotFoundException;
import com.bcf.ero.countryroutes.model.Route;
import com.bcf.ero.countryroutes.service.impl.RouteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routing")
@RequiredArgsConstructor
public class RouteController {

    private final RouteServiceImpl routeService;

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<Route> findRoute(@PathVariable String origin,
                                          @PathVariable String destination) {
        try {
            return new ResponseEntity<>(routeService.findRoute(origin, destination), HttpStatus.OK);
        }catch (PathNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
