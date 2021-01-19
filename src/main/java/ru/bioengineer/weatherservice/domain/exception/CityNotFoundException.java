package ru.bioengineer.weatherservice.domain.exception;

public class CityNotFoundException extends RuntimeException {

    private static final int CODE = 404;
    private final String cityName;

    public CityNotFoundException(String cityName) {
        super(String.format("City [%s] not found", cityName));
        this.cityName = cityName;
    }

    public int getCode() {
        return CODE;
    }

    public String getCityName() {
        return cityName;
    }
}
