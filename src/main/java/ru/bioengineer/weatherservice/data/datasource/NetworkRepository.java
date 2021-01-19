package ru.bioengineer.weatherservice.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.bioengineer.weatherservice.data.entity.openweather.ResponseEntityDTO;
import ru.bioengineer.weatherservice.domain.datasource.IWeatherRepository;
import ru.bioengineer.weatherservice.domain.entity.Weather;
import ru.bioengineer.weatherservice.domain.utils.RestService;

import java.util.Optional;

@Service
public class NetworkRepository implements IWeatherRepository.INetworkRepository {

    private static final Logger logger = LoggerFactory.getLogger(NetworkRepository.class);

    private final Environment env;
    private final RestService restService;

    @Autowired
    public NetworkRepository(Environment env, RestService restService) {
        this.env = env;
        this.restService = restService;
    }

    @Override
    public Optional<Weather> findByCityName(String cityName) {
        try {
            Weather weather = restService
                    .sendGet(getUrl(cityName), ResponseEntityDTO.class)
                    .convert();

            return Optional.of(weather);
        } catch (HttpClientErrorException.NotFound e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    private String getUrl(String cityName) {
        String baseUrl = env.getProperty("WEATHER_SERVICE_URL");
        String defaultLang = env.getProperty("WEATHER_SERVICE_DEFAULT_LANG");
        String serviceToken = env.getProperty("WEATHER_SERVICE_TOKEN");

        return String.format("%s?q=%s&lang=%s&appid=%s", baseUrl, cityName, defaultLang, serviceToken);
    }

}
