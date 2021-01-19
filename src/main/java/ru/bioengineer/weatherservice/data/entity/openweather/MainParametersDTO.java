package ru.bioengineer.weatherservice.data.entity.openweather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bioengineer.weatherservice.data.entity.EntityConverter;
import ru.bioengineer.weatherservice.domain.entity.Parameters;

import java.util.Objects;

@JsonAutoDetect
public class MainParametersDTO implements EntityConverter<Parameters> {

    @JsonProperty("temp_min")
    public int minTemperature;

    @JsonProperty("temp_max")
    public int maxTemperature;

    @JsonProperty("pressure")
    public int pressure;

    @JsonProperty("humidity")
    public int humidity;

    public MainParametersDTO() {
    }

    @Override
    public Parameters convert() {
        return new Parameters(this.minTemperature, this.maxTemperature, this.pressure, this.humidity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainParametersDTO that = (MainParametersDTO) o;
        return minTemperature == that.minTemperature
                && maxTemperature == that.maxTemperature
                && pressure == that.pressure
                && humidity == that.humidity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minTemperature, maxTemperature, pressure, humidity);
    }
}


