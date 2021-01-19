package ru.bioengineer.weatherservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public final class Weather {

    private final City city;
    private final String shortDescription;
    private final String fullDescription;
    private final Parameters parameters;
    private final Wind wind;
    private final long refreshDate;

    public Weather(City city, String shortText, String fullText, Parameters params, Wind wind, long refreshDate) {
        this.city = city;
        this.shortDescription = shortText;
        this.fullDescription = fullText;
        this.parameters = params;
        this.wind = wind;
        this.refreshDate = refreshDate;
    }

    public City getCity() {
        return city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public Wind getWind() {
        return wind;
    }

    public long getRefreshDate() {
        return refreshDate;
    }

    /**
     * Проверяет, как давно обновлялась погода.
     * Если погода была получена больше 1 часа назад, возвращает true
     */
    @JsonIgnore
    public boolean isWeatherOld() {
        long lifeTime = (System.currentTimeMillis() - this.refreshDate);
        double lifeTimeInMin = (lifeTime / 1000.0) / 60.0;
        return lifeTimeInMin >= 60;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return city.equals(weather.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city=" + city +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", parameters=" + parameters +
                ", wind=" + wind +
                ", createTime=" + refreshDate +
                '}';
    }
}
