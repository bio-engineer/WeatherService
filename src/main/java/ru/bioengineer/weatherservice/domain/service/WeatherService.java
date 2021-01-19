package ru.bioengineer.weatherservice.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.WeatherRequest;
import ru.bioengineer.weatherservice.domain.entity.exception.CityNotFound;
import ru.bioengineer.weatherservice.domain.entity.exception.TooManyCitiesFound;
import ru.bioengineer.weatherservice.domain.exception.CityNotFoundException;
import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

/**
 * Обрабатывает входящие запросы от пользователей
 */
@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final IWeatherRepository weatherRepository;

    @Autowired
    public WeatherService(IWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    /**
     * По переданным параметрам ищет погоду в репозитории
     * Если погода не найдена, или найдено несколько городов с одинаковым названием
     * возвращает пользователю соответствующее сообщение
     *
     * @see IWeatherRepository
     */
    public Object handleRequest(WeatherRequest request) {
        try {
            return weatherRepository.findByCityName(request.getCityName());
        } catch (CityNotFoundException e) {
            logger.debug(e.getMessage());
            return new CityNotFound(e);
        } catch (TooManyCitiesFoundException e) {
            logger.debug(e.getMessage());
            return new TooManyCitiesFound(e);
        }
    }


}
