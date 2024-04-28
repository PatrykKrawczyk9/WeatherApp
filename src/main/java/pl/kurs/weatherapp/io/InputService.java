package pl.kurs.weatherapp.io;

import pl.kurs.weatherapp.data.Option;

import java.time.LocalDate;

public interface InputService {
    Option chooseOption();

    LocalDate getDateFromUser();

    String getCityFromUser();
}
