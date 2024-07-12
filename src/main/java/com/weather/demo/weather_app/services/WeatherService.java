package com.weather.demo.weather_app.services;

import com.weather.demo.weather_app.entities.Location;
import com.weather.demo.weather_app.entities.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.weather.url}")
    private String weatherApiUrl;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Location getLocation(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("limit", 1)
                .queryParam("appid", apiKey)
                .toUriString();

        Location[] locations = restTemplate.getForObject(url, Location[].class);
        if (locations != null && locations.length > 0) {
            return locations[0];
        }
        return null;
    }

    public Weather getWeather(double lat, double lon) {
        String url = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, Weather.class);
    }
}
