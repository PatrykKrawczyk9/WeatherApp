package pl.kurs.weatherapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.weatherapp.models.CurrentWeatherCondition;

public interface CurrentWeatherConditionRepository extends JpaRepository<CurrentWeatherCondition, Long> {
}
