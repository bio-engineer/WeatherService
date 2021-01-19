package ru.bioengineer.weatherservice.data.entity.openweather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonAutoDetect
public class DescriptionDTO {

    @JsonProperty("main")
    public String shortDescription;

    @JsonProperty("description")
    public String fullDescription;

    public DescriptionDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescriptionDTO that = (DescriptionDTO) o;
        return shortDescription.equals(that.shortDescription) && fullDescription.equals(that.fullDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortDescription, fullDescription);
    }
}

