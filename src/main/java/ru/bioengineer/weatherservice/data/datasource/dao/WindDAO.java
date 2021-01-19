package ru.bioengineer.weatherservice.data.datasource.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bioengineer.weatherservice.data.entity.db.WindDTO;

public interface WindDAO extends JpaRepository<WindDTO, Integer> {
}
