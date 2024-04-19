package pl.kurs.weatherapp.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.kurs.weatherapp.dao.ICurrentWeatherConditionDao;
import pl.kurs.weatherapp.dao.IForecastWeatherConditionDao;
import pl.kurs.weatherapp.dao.JpaCurrentWeatherConditionDao;
import pl.kurs.weatherapp.dao.JpaForecastWeatherConditionDao;
import pl.kurs.weatherapp.io.ConsolePrinter;
import pl.kurs.weatherapp.io.DataReader;
import pl.kurs.weatherapp.io.InputService;
import pl.kurs.weatherapp.io.OutputService;
import pl.kurs.weatherapp.services.IUrlBuild;
import pl.kurs.weatherapp.services.IWeatherService;
import pl.kurs.weatherapp.services.WeatherApiService;
import pl.kurs.weatherapp.services.WeatherUrlBuild;
import pl.kurs.weatherapp.utils.ObjectMapperHolder;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        InputService inputService = new DataReader(new Scanner(System.in));
        OutputService outputService = new ConsolePrinter();
        IUrlBuild urlBuild = new WeatherUrlBuild();
        ICurrentWeatherConditionDao currentWeatherConditionDao = new JpaCurrentWeatherConditionDao();
        IForecastWeatherConditionDao forecastWeatherConditionDao = new JpaForecastWeatherConditionDao();
        IWeatherService weatherService = new WeatherApiService(ObjectMapperHolder.INSTANCE.getObjectMapper(), urlBuild, currentWeatherConditionDao, forecastWeatherConditionDao);

        WeatherController controller = new WeatherController(inputService, outputService, urlBuild, weatherService);

        controller.mainLoop();

    }
}
