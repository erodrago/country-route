package com.bcf.ero.countryroutes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @JsonProperty(value = "cca3")
    private String name;

    private List<String> borders;
}
