package ru.bioengineer.weatherservice.data.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.exception.CityNotFoundException;

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
    public Weather findByCityName(String cityName) {
        return localRepository.findByCityName(cityName)
                .map(weather -> {
                    if (weather.isWeatherOld()) return getNewWeather(cityName);
                    else return weather;
                })
                .orElse(getNewWeather(cityName));
    }

    private Weather getNewWeather(String cityName) {
        return networkRepository
                .findByCityName(cityName)
                .map(localRepository::save)
                .orElseThrow(() -> new CityNotFoundException(cityName));
    }
}
