package ru.bioengineer.weatherservice.data.datasource.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bioengineer.weatherservice.data.entity.db.CoordinatesDTO;

public interface CoordinatesDAO extends JpaRepository<CoordinatesDTO, Integer> {
}
