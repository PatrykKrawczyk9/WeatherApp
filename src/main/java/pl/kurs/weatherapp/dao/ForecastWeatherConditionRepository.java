package pl.kurs.weatherapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

public interface ForecastWeatherConditionRepository extends JpaRepository<ForecastWeatherCondition, Long> {
}
