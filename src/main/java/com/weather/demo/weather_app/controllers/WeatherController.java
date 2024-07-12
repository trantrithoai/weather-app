package com.weather.demo.weather_app.controllers;

import com.weather.demo.weather_app.entities.Location;
import com.weather.demo.weather_app.entities.Weather;
import com.weather.demo.weather_app.services.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/location")
    public Location getLocation(@RequestParam String city) {
        return weatherService.getLocation(city);
    }

    @GetMapping("/weather")
    public Weather getWeather(@RequestParam String city) {
        Location location = weatherService.getLocation(city);
        if (location != null) {
            return weatherService.getWeather(location.getLat(), location.getLon());
        }
        return null;
    }
}
