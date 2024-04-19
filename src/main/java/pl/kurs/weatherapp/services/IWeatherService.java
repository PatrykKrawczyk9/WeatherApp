package pl.kurs.weatherapp.services;

import pl.kurs.weatherapp.exceptions.InvalidDataException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IWeatherService {

    double getCurrentWeatherTemp(String city) throws InvalidDataException;

    double getForecastWeather(String city, LocalDate date) throws InvalidDataException;
}
