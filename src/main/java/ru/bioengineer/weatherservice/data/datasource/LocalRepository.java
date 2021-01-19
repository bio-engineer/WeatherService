package ru.bioengineer.weatherservice.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bioengineer.weatherservice.data.datasource.dao.*;
import ru.bioengineer.weatherservice.data.entity.db.*;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.City;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Хранит информацию о погоде в БД
 */
@Service
public class LocalRepository implements IWeatherRepository.ILocalRepository {

    private static final Logger logger = LoggerFactory.getLogger(LocalRepository.class);

    private final CityDAO cityDAO;
    private final CoordinatesDAO coordinatesDAO;
    private final ParametersDAO parametersDAO;
    private final WeatherDAO weatherDAO;
    private final WindDAO windDAO;

    @Autowired
    public LocalRepository(
            CityDAO cityDAO,
            CoordinatesDAO coordinatesDAO,
            ParametersDAO parametersDAO,
            WeatherDAO weatherDAO,
            WindDAO windDAO
    ) {
        this.cityDAO = cityDAO;
        this.coordinatesDAO = coordinatesDAO;
        this.parametersDAO = parametersDAO;
        this.weatherDAO = weatherDAO;
        this.windDAO = windDAO;
    }

    @Override
    public Weather save(Weather weather) {
        City city = weather.getCity();

        CoordinatesDTO coordinatesDTO = getCoordinates(city);
        WindDTO windDTO = getWind(weather, city.getId());
        ParametersDTO paramsDTO = getParams(weather, city.getId());
        WeatherDTO weatherDTO = getWeather(weather, city);
        CityDTO cityDTO = getCityDTO(weather, city, coordinatesDTO);

        try {
            coordinatesDAO.saveAndFlush(coordinatesDTO);
            windDAO.saveAndFlush(windDTO);
            parametersDAO.saveAndFlush(paramsDTO);
            weatherDAO.saveAndFlush(weatherDTO);
            cityDAO.saveAndFlush(cityDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return cityDAO.findById(city.getId())
                .map(CityDTO::convert)
                .orElseThrow(() -> new RuntimeException("Error while saving to DB"));
    }


    @Override
    public Optional<Weather> findByCityName(String cityName) {
        List<CityDTO> cities;

        try {
            cities = cityDAO.findByCityName(cityName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }

        if (cities.size() > 1) {
            throw new TooManyCitiesFoundException();
        } else if (cities.size() == 1) {
            return Optional.of(cities.get(0).convert());
        } else return Optional.empty();
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
