package ru.bioengineer.weatherservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bioengineer.weatherservice.domain.entity.WeatherRequest;
import ru.bioengineer.weatherservice.domain.service.WeatherService;

@RestController
public class ClientController {

    private final WeatherService weatherService;

    @Autowired
    public ClientController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/weather")
    public Object getWeatherByCityName(@RequestBody WeatherRequest request) {
        return weatherService.handleRequest(request);
    }
}
