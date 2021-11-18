package com.bcf.ero.countryroutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PathNotFoundException extends Exception{

    public PathNotFoundException(String message){
        super(message);
    }
}
