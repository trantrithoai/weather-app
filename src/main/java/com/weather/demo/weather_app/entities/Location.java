package com.weather.demo.weather_app.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Location {
    // Getters and setters
    private String name;
    private double lat;
    private double lon;
    private String country;
    private String state;
}