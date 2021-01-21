package ru.bioengineer.weatherservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.entity.WeatherRequest;
import ru.bioengineer.weatherservice.domain.service.GetWeatherUseCase;

@RestController
public class ClientController {

    private final GetWeatherUseCase getWeatherUseCase;

    @Autowired
    public ClientController(GetWeatherUseCase getWeatherUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
    }

    @PostMapping("/weather")
    public Mono<Weather> getWeatherByCityName(@RequestBody WeatherRequest request) {
        return getWeatherUseCase.execute(request);
    }
}
