package ru.bioengineer.weatherservice.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.entity.exception.CityNotFound;
import ru.bioengineer.weatherservice.domain.entity.exception.TooManyCitiesFound;
import ru.bioengineer.weatherservice.domain.exception.CityNotFoundException;
import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

/**
 * Обрабатывает входящие запросы от пользователей
 */
@Service
public class GetWeatherUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetWeatherUseCase.class);

    private final IWeatherRepository weatherRepository;

    @Autowired
    public GetWeatherUseCase(IWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    /**
     * По переданным параметрам ищет погоду в репозитории
     * Если погода не найдена, или найдено несколько городов с одинаковым названием
     * возвращает пользователю соответствующее сообщение
     *
     * @see IWeatherRepository
     */
    public Mono<Weather> execute(String cityName) {
        return weatherRepository
                .findByCityName(cityName)
                .doOnError(CityNotFoundException.class, CityNotFound::new)
                .doOnError(TooManyCitiesFoundException.class, TooManyCitiesFound::new);
    }

}
