package ru.bioengineer.weatherservice.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.bioengineer.weatherservice.data.datasource.dao.CityDAO;
import ru.bioengineer.weatherservice.data.entity.db.*;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.City;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

/**
 * Хранит информацию о погоде в БД
 */
@Service
public class LocalRepository implements IWeatherRepository.ILocalRepository {

    private static final Logger logger = LoggerFactory.getLogger(LocalRepository.class);

    private final CityDAO cityDAO;

    @Autowired
    public LocalRepository(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public Mono<Weather> save(Weather weather) {
        return Mono
                .fromCallable(() -> {
                    City city = weather.getCity();
                    CityDTO cityDTO = getCityDTO(weather, weather.getCity(), getCoordinates(city));

                    cityDAO.saveAndFlush(cityDTO);
                    return city.getId();
                })
                .map(cityId -> cityDAO.findById(cityId)
                        .map(CityDTO::convert)
                        .orElseThrow(() -> new RuntimeException("Error while saving to DB"))
                );

    }


    @Override
    public Mono<Weather> findByCityName(String cityName) {
        return Mono
                .fromCallable(() -> cityDAO.findByCityName(cityName))
                .flatMap(cityDTOS -> {
                    if (cityDTOS.size() > 1) {
                        return Mono.error(TooManyCitiesFoundException::new);
                    } else if (cityDTOS.size() == 1) {
                        return Mono.just(cityDTOS.get(0).convert());
                    } else return Mono.empty();
                });

    }

    private CoordinatesDTO getCoordinates(City city) {
        return new CoordinatesDTO(
                city.getId(),
                city.getCoordinates().getLatitude(),
                city.getCoordinates().getLongitude()
        );
    }

    private WindDTO getWind(Weather weather, Integer id) {
        return new WindDTO(
                id,
                weather.getWind().getSpeed(),
                weather.getWind().getDirection()
        );
    }

    private ParametersDTO getParams(Weather weather, Integer id) {
        return new ParametersDTO(
                id,
                weather.getParameters().getMinTemp(),
                weather.getParameters().getMaxTemp(),
                weather.getParameters().getPressure(),
                weather.getParameters().getHumidity()
        );
    }

    private WeatherDTO getWeather(Weather weather, City city) {
        return new WeatherDTO(
                city.getId(),
                weather.getShortDescription(),
                weather.getFullDescription(),
                getParams(weather, city.getId()),
                getWind(weather, city.getId()),
                weather.getRefreshDate()
        );
    }

    private CityDTO getCityDTO(Weather weather, City city, CoordinatesDTO coordinatesDTO) {
        return new CityDTO(
                city.getId(),
                weather.getCity().getCityName(),
                coordinatesDTO,
                getWeather(weather, city)
        );
    }
}
