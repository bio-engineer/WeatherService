package ru.bioengineer.weatherservice.data.entity.db;

import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.Coordinates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "coordinates")
public class CoordinatesDTO implements EntityConverter<Coordinates> {

    @Id
    @Column(name = "city_id")
    public Integer id;

    @Column(name = "latitude")
    public double latitude;  // широта

    @Column(name = "longitude")
    public double longitude; // долгота

    public CoordinatesDTO() {
    }

    public CoordinatesDTO(Integer id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
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
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CoordinatesDTO{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
