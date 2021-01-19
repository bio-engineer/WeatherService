package ru.bioengineer.weatherservice.data.entity.openweather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.City;
import ru.bioengineer.weatherservice.domain.entity.Weather;

import java.util.List;
import java.util.Objects;

/**
 * Сущность погоды от OpenWeather
 */

@JsonAutoDetect
public class ResponseEntityDTO implements EntityConverter<Weather> {

    @JsonProperty("id")
    public int cityId;

    @JsonProperty("name")
    public String cityName;

    @JsonProperty("coord")
    public CoordinatesDTO coordinatesDTO;

    @JsonProperty("main")
    public MainParametersDTO mainParametersDTO;

    @JsonProperty("weather")
    public List<DescriptionDTO> descriptionsList;

    @JsonProperty("wind")
    public WindDTO windDTO;

    public ResponseEntityDTO() {
    }

    @Override
    public Weather convert() {
        return new Weather(
                convertToCity(),
                this.descriptionsList.get(0).shortDescription,
                this.descriptionsList.get(0).fullDescription,
                this.mainParametersDTO.convert(),
                this.windDTO.convert(),
                System.currentTimeMillis()
        );
    }

    private City convertToCity() {
        return new City(this.cityId, this.cityName, this.coordinatesDTO.convert());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEntityDTO that = (ResponseEntityDTO) o;
        return cityName.equals(that.cityName)
                && coordinatesDTO.equals(that.coordinatesDTO)
                && mainParametersDTO.equals(that.mainParametersDTO)
                && descriptionsList.equals(that.descriptionsList)
                && windDTO.equals(that.windDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, coordinatesDTO, mainParametersDTO, descriptionsList, windDTO);
    }
}
