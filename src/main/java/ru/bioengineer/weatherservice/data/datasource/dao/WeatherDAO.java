package ru.bioengineer.weatherservice.data.datasource.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bioengineer.weatherservice.data.entity.db.WeatherDTO;

public interface WeatherDAO extends JpaRepository<WeatherDTO, Integer> {
}
