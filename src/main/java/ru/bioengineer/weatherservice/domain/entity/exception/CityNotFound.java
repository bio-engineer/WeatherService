package ru.bioengineer.weatherservice.domain.entity.exception;

import ru.bioengineer.weatherservice.domain.exception.CityNotFoundException;

public class CityNotFound {

    private final int code;
    private final String cityName;

    public CityNotFound(CityNotFoundException e) {
        this.code = e.getCode();
        this.cityName = e.getCityName();
    }

    public int getCode() {
        return code;
    }

    public String getCityName() {
        return cityName;
    }
}
