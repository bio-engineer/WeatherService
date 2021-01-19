package ru.bioengineer.weatherservice.domain.entity;

import java.util.Objects;

public class Parameters {

    private final int minTemp;
    private final int maxTemp;
    private final int pressure;  // давление
    private final int humidity;  // влажность

    public Parameters(int minTemp, int maxTemp, int pressure, int humidity) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return minTemp == that.minTemp && maxTemp == that.maxTemp && pressure == that.pressure && humidity == that.humidity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minTemp, maxTemp, pressure, humidity);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
