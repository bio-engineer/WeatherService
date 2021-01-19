package ru.bioengineer.weatherservice.domain.datasource;

import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.exception.CityNotFoundException;
import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

import java.util.Optional;

/**
 * Репозиторий погоды
 */
public interface IWeatherRepository {

    Weather findByCityName(String cityName) throws TooManyCitiesFoundException, CityNotFoundException;

    /**
     * Локальный репозиторий с погодой
     */
    interface ILocalRepository {

        Weather save(Weather weather);

        Optional<Weather> findByCityName(String cityName) throws TooManyCitiesFoundException;
    }

    /**
     * Репозиторий внешнего сервиса
     */
    interface INetworkRepository {
        Optional<Weather> findByCityName(String cityName);
    }
}
