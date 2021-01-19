package ru.bioengineer.weatherservice.domain.entity;

import java.util.Objects;

/**
 * Ветер
 */
public class Wind {

    private final int speed;  // скорость
    private final int direction;  // направление

    public Wind(int speed, int direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
        return speed == wind.speed && direction == wind.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, direction);
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", direction=" + direction +
                '}';
    }
}
