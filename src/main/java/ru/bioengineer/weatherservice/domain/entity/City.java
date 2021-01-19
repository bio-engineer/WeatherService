package ru.bioengineer.weatherservice.domain.entity;

import java.util.Objects;

public class City {

    private final Integer id;
    private final String cityName;
    private final Coordinates coordinates;

    public City(Integer id, String cityName, Coordinates coordinates) {
        this.id = id;
        this.cityName = cityName;
        this.coordinates = coordinates;
    }

    public Integer getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
