package com.weather.demo.weather_app;

import com.weather.demo.weather_app.entities.Location;
import com.weather.demo.weather_app.entities.Weather;
import com.weather.demo.weather_app.entities.WeatherDetails;
import com.weather.demo.weather_app.services.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(weatherService, "apiKey", "dummy_api_key");
        ReflectionTestUtils.setField(weatherService, "apiUrl", "http://api.openweathermap.org/geo/1.0/direct");
        ReflectionTestUtils.setField(weatherService, "weatherApiUrl", "https://api.openweathermap.org/data/2.5/weather");
    }

    @Test
    public void testGetLocation() {
        Location mockLocation = new Location();
        mockLocation.setName("London");
        mockLocation.setLat(51.5073219);
        mockLocation.setLon(-0.1276474);
        mockLocation.setCountry("GB");
        mockLocation.setState("England");

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(new Location[]{mockLocation});

        Location location = weatherService.getLocation("London");

        assertNotNull(location);
        assertEquals("London", location.getName());
        assertEquals(51.5073219, location.getLat());
        assertEquals(-0.1276474, location.getLon());
        assertEquals("GB", location.getCountry());
        assertEquals("England", location.getState());
    }

    @Test
    public void testGetWeather() {
        Weather mockWeather = new Weather();
        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setMain("Clouds");
        weatherDetails.setDescription("broken clouds");
        mockWeather.setWeather(new WeatherDetails[]{weatherDetails});

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(mockWeather);

        Weather weather = weatherService.getWeather(51.5073219, -0.1276474);

        assertNotNull(weather);
        assertNotNull(weather.getWeather());
        assertEquals(1, weather.getWeather().length);
        assertEquals("Clouds", weather.getWeather()[0].getMain());
        assertEquals("broken clouds", weather.getWeather()[0].getDescription());
    }
}