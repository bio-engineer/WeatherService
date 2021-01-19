package ru.bioengineer.weatherservice.data.entity.db;

import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.Parameters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "params")
public class ParametersDTO implements EntityConverter<Parameters> {

    @Id
    @Column(name = "city_id")
    public Integer cityId;

    @Column(name = "min_temp")
    public int minTemp;

    @Column(name = "max_temp")
    public int maxTemp;

    @Column(name = "pressure")
    public int pressure;

    @Column(name = "humidity")
    public int humidity;

    public ParametersDTO() {
    }

    public ParametersDTO(Integer cityId, int minTemp, int maxTemp, int pressure, int humidity) {
        this.cityId = cityId;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @Override
    public Parameters convert() {
        return new Parameters(this.minTemp, this.maxTemp, this.pressure, this.humidity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParametersDTO that = (ParametersDTO) o;
        return cityId.equals(that.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }

    @Override
    public String toString() {
        return "ParametersDTO{" +
                "cityId=" + cityId +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
