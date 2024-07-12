package com.weather.demo.weather_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private WeatherDetails[] weather;
    private String name;
}