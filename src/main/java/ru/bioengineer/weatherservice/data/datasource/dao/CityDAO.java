package ru.bioengineer.weatherservice.data.datasource.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bioengineer.weatherservice.data.entity.db.CityDTO;

import java.util.List;

public interface CityDAO extends JpaRepository<CityDTO, Integer> {

    List<CityDTO> findByCityName(String cityName);
}
