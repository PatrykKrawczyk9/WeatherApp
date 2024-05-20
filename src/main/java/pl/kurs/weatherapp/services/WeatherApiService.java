package pl.kurs.weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.kurs.weatherapp.dao.CurrentWeatherConditionRepository;
import pl.kurs.weatherapp.dao.ForecastWeatherConditionRepository;
import pl.kurs.weatherapp.exceptions.InvalidDataException;
import pl.kurs.weatherapp.models.CurrentWeatherCondition;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WeatherApiService implements IWeatherService {

    private ObjectMapper objectMapper;
    private IUrlBuild urlBuild;
    private CurrentWeatherConditionRepository currentWeatherConditionRepository;
    private ForecastWeatherConditionRepository forecastWeatherConditionRepository;

    public WeatherApiService(
            ObjectMapper objectMapper,
            IUrlBuild urlBuild,
            CurrentWeatherConditionRepository currentWeatherConditionRepository,
            ForecastWeatherConditionRepository forecastWeatherConditionRepository) {
        this.objectMapper = objectMapper;
        this.urlBuild = urlBuild;
        this.currentWeatherConditionRepository = currentWeatherConditionRepository;
        this.forecastWeatherConditionRepository = forecastWeatherConditionRepository;
    }

    @Override
    public double getCurrentWeatherTemp(String city) throws InvalidDataException {
        JsonNode node = null;
        String stringUrl = urlBuild.buildUrl(city);

        try {
            node = objectMapper.readTree(new URL(stringUrl));
        } catch (IOException e) {
            throw new InvalidDataException("Niepoprawnie podana miejscowosc", e);
        }

        double avgTemp = node.get("current").get("temp_c").asDouble();

        CurrentWeatherCondition weatherCondition = new CurrentWeatherCondition(city, LocalDateTime.now(), avgTemp);
        currentWeatherConditionRepository.save(weatherCondition);
        System.out.println(weatherCondition.getAvgTemp());

        return avgTemp;
    }

    @Override
    public double getForecastWeather(String city, LocalDate date) throws InvalidDataException {
        JsonNode node = null;
        String stringUrl = urlBuild.buildUrl(city);

        try {
            node = objectMapper.readTree(new URL(stringUrl));
        } catch (IOException e) {
            throw new InvalidDataException("Niepoprawnie podana miejscowosc", e);
        }

        double avgTemp;

        JsonNode forecastNode = node.get("forecast").get("forecastday");
        for (JsonNode dayNode : forecastNode) {
            String forecastDate = dayNode.get("date").asText();
            if (date.toString().equals(forecastDate)) {
                avgTemp = dayNode.get("day").get("avgtemp_c").asDouble();
                ForecastWeatherCondition weatherCondition = new ForecastWeatherCondition(city, date, avgTemp);
                forecastWeatherConditionRepository.save(weatherCondition);
                return avgTemp;
            }
        }
        throw new InvalidDataException("Nie znaleziono prognozy dla podanej daty: " + date);
    }


}
