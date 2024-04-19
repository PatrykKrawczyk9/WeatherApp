package pl.kurs.weatherapp.dao;

import pl.kurs.weatherapp.models.CurrentWeatherCondition;

public interface ICurrentWeatherConditionDao {
    void save(CurrentWeatherCondition weatherCondition);
}
