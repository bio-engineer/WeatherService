package ru.bioengineer.weatherservice.data.entity.openweather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.Coordinates;

import java.util.Objects;

@JsonAutoDetect
public class CoordinatesDTO implements EntityConverter<Coordinates> {

    @JsonProperty("lat")
    public double latitude;  // широта

    @JsonProperty("lon")
    public double longitude; // долгота

    public CoordinatesDTO() {
    }

    @Override
    public Coordinates convert() {
        return new Coordinates(this.latitude, this.longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesDTO that = (CoordinatesDTO) o;
        return Double.compare(that.longitude, longitude) == 0 && Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
