package com.weather.demo.weather_app.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherDetails {
    private int id;
    private String main;
    private String description;
    private String icon;

}
