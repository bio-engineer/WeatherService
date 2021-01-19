package ru.bioengineer.weatherservice.data.entity.openweather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.Wind;

import java.util.Objects;

@JsonAutoDetect
public class WindDTO implements EntityConverter<Wind> {

    @JsonProperty("speed")
    public int speed;  // скорость ветра

    @JsonProperty("deg")
    public int direction;  // направление ветра

    public WindDTO() {
    }

    @Override
    public Wind convert() {
        return new Wind(this.speed, this.direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WindDTO windDTO = (WindDTO) o;
        return speed == windDTO.speed && direction == windDTO.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, direction);
    }
}
