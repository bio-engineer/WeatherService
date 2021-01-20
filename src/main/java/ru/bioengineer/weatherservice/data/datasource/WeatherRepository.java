package ru.bioengineer.weatherservice.data.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.Weather;

@Service
public class WeatherRepository implements IWeatherRepository {

    private final INetworkRepository networkRepository;
    private final ILocalRepository localRepository;

    @Autowired
    public WeatherRepository(ILocalRepository localRepository, INetworkRepository networkRepository) {
        this.localRepository = localRepository;
        this.networkRepository = networkRepository;
    }

    @Override
    public Mono<Weather> findByCityName(String cityName) {
        return localRepository
                .findByCityName(cityName)
                .flatMap(weather -> {
                    if (weather.isWeatherOld()) return getNewWeather(cityName);
                    else return Mono.just(weather);
                })
                .switchIfEmpty(getNewWeather(cityName));
    }

    private Mono<Weather> getNewWeather(String cityName) {
        return networkRepository
                .findByCityName(cityName)
                .flatMap(localRepository::save);
    }
}
