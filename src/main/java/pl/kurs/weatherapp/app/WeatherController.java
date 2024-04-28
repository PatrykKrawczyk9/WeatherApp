package pl.kurs.weatherapp.app;

import org.springframework.stereotype.Controller;
import pl.kurs.weatherapp.data.Option;
import pl.kurs.weatherapp.exceptions.InvalidDataException;
import pl.kurs.weatherapp.io.InputService;
import pl.kurs.weatherapp.io.OutputService;
import pl.kurs.weatherapp.services.IWeatherService;

import java.time.LocalDate;

@Controller
public class WeatherController {

    private InputService dataReader;
    private OutputService printer;
    private IWeatherService weatherService;

    public WeatherController(InputService dataReader, OutputService printer, IWeatherService weatherService) {
        this.dataReader = dataReader;
        this.printer = printer;
        this.weatherService = weatherService;
    }

    public void mainLoop() {
        Option option;
        do {
            option = dataReader.chooseOption();
            executeOption(option);
        } while (option != Option.EXIT);
    }

    private void executeOption(Option option) {
        try {
            switch (option) {
                case CURRENT_WEATHER -> {
                    String city = dataReader.getCityFromUser();
                    double currentWeatherTemp = weatherService.getCurrentWeatherTemp(city);
                    printer.printLine("Aktualna temperatura w miejscowosci " + city + " to: " + currentWeatherTemp + "C");
                }
                case AVG_FORECAST_WEATHER -> {
                    String city = dataReader.getCityFromUser();
                    LocalDate dateFromUser = dataReader.getDateFromUser();
                    double forecastWeather = weatherService.getForecastWeather(city, dateFromUser);
                    printer.printLine("Srednia temperatura w dniu + " + dateFromUser + " bedzie wynosic: " + forecastWeather + "C");
                }
                case EXIT -> printer.exit();
            }
        } catch (InvalidDataException e) {
            handleException(e);
            printer.printOption();
        }
    }

    private void handleException(Exception e) {
        if (e.getMessage() != null) {
            System.err.println(e.getMessage());
        } else
            printer.printLine("Błąd wprowadzenia");
    }


}
