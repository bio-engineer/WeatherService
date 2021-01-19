package ru.bioengineer.weatherservice.data.entity.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "weather")
public final class WeatherDTO {

    @Id
    @Column(name = "city_id")
    public Integer cityId;

    @Column(name = "short_description")
    public String shortDescription;

    @Column(name = "full_description")
    public String fullDescription;

    @OneToOne
    @JoinColumn(name = "params_id")
    public ParametersDTO parametersDTO;

    @OneToOne
    @JoinColumn(name = "wind_id")
    public WindDTO windDTO;

    @Column(name = "create_time")
    public long createTime;

    public WeatherDTO() {
    }

    public WeatherDTO(Integer cityId, String shortText, String fullText,
                      ParametersDTO params, WindDTO wind, long createTime
    ) {
        this.cityId = cityId;
        this.shortDescription = shortText;
        this.fullDescription = fullText;
        this.parametersDTO = params;
        this.windDTO = wind;
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDTO that = (WeatherDTO) o;
        return cityId.equals(that.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "cityId=" + cityId +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", parametersDTO=" + parametersDTO +
                ", windDTO=" + windDTO +
                ", createTime=" + createTime +
                '}';
    }
}
