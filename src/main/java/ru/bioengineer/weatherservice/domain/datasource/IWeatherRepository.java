package ru.bioengineer.weatherservice.domain.datasource;

import reactor.core.publisher.Mono;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

/**
 * Репозиторий погоды
 */
public interface IWeatherRepository {

    /**
     * Ищет погоду в указанном городе
     * Может возвращать Mono.error c TooManyCitiesFoundException
     *
     * @see TooManyCitiesFoundException
     */
    Mono<Weather> findByCityName(String cityName);

    /**
     * Локальный репозиторий с погодой
     */
    interface ILocalRepository {

        Mono<Weather> save(Weather weather);

        /**
         * Ищет погоду в локальном репозитории
         * Может возвращать Mono.error c TooManyCitiesFoundException
         *
         * @see TooManyCitiesFoundException
         */
        Mono<Weather> findByCityName(String cityName);
    }

    /**
     * Репозиторий внешнего сервиса
     */
    interface INetworkRepository {
        Mono<Weather> findByCityName(String cityName);
    }
}
