package ru.bioengineer.weatherservice.data.datasource.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bioengineer.weatherservice.data.entity.db.ParametersDTO;

public interface ParametersDAO extends JpaRepository<ParametersDTO, Integer> {
}
