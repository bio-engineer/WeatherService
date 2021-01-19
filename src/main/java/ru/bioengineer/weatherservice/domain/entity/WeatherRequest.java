package ru.bioengineer.weatherservice.domain.entity;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Optional;

/**
 * Запрос от пользователя, используемый для получения погоды
 */
public class WeatherRequest {

    private final String cityName;
    private final String lang;

    public WeatherRequest(String cityName, @Nullable String lang) {
        this.cityName = cityName;
        this.lang = lang;
    }

    public String getCityName() {
        return cityName;
    }

    public Optional<String> getLang() {
        return Optional.ofNullable(lang);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherRequest that = (WeatherRequest) o;
        return cityName.equals(that.cityName) && Objects.equals(lang, that.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, lang);
    }

    @Override
    public String toString() {
        return "WeatherRequest{" +
                "cityName='" + cityName + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
