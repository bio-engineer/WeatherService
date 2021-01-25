package ru.bioengineer.weatherservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.service.GetWeatherUseCase;

@RestController
public class ClientController {

    private final GetWeatherUseCase getWeatherUseCase;

    @Autowired
    public ClientController(GetWeatherUseCase getWeatherUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
    }

    @GetMapping("/weather")
    public Mono<Weather> getWeatherByCityName(@RequestParam(name = "city") String cityName) {
        return getWeatherUseCase.execute(cityName);
    }
}
