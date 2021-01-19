package ru.bioengineer.weatherservice.data.entity.db;

import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.Wind;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "wind")
public class WindDTO implements EntityConverter<Wind> {

    @Id
    @Column(name = "city_id")
    public Integer cityId;

    @Column(name = "speed")
    public int speed;

    @Column(name = "direction")
    public int direction;

    public WindDTO() {
    }

    public WindDTO(Integer cityId, int speed, int direction) {
        this.cityId = cityId;
        this.speed = speed;
        this.direction = direction;
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
        return cityId.equals(windDTO.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }

    @Override
    public String toString() {
        return "WindDTO{" +
                "cityId=" + cityId +
                ", speed=" + speed +
                ", direction=" + direction +
                '}';
    }
}
