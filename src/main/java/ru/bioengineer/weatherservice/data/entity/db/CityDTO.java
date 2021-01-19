package ru.bioengineer.weatherservice.data.entity.db;

import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.City;
import ru.bioengineer.weatherservice.domain.entity.Weather;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cities")
public class CityDTO implements EntityConverter<Weather> {

    @Id
    @Column(name = "city_id")
    public Integer id;

    @Column(name = "city_name")
    public String cityName;

    @OneToOne
    @JoinColumn(name = "coord_id")
    public CoordinatesDTO coordinates;

    @OneToOne
    @JoinColumn(name = "weather_id")
    public WeatherDTO weather;

    public CityDTO() {
    }

    public CityDTO(Integer id, String cityName, CoordinatesDTO coordinates, WeatherDTO weather) {
        this.id = id;
        this.cityName = cityName;
        this.coordinates = coordinates;
        this.weather = weather;
    }

    @Override
    public Weather convert() {
        return new Weather(
                convertToCity(),
                this.weather.shortDescription,
                this.weather.fullDescription,
                this.weather.parametersDTO.convert(),
                this.weather.windDTO.convert(),
                this.weather.createTime
        );
    }

    private City convertToCity() {
        return new City(
                this.id,
                this.cityName,
                this.coordinates.convert()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return id.equals(cityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", coordinates=" + coordinates +
                ", weather=" + weather +
                '}';
    }
}
