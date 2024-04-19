package pl.kurs.weatherapp.dao;

import pl.kurs.weatherapp.models.CurrentWeatherCondition;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

public interface IForecastWeatherConditionDao {
    void save(ForecastWeatherCondition weatherCondition);
}
