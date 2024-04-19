package pl.kurs.weatherapp.services;

import org.springframework.stereotype.Service;
import pl.kurs.weatherapp.config.AppConfig;

@Service
public class WeatherUrlBuild implements IUrlBuild {

    @Override
    public String buildUrl(String city) {
        StringBuilder sb = new StringBuilder();
        sb.append(AppConfig.FORECAST_BASE_URL);
        sb.append(AppConfig.API_KEY);
        sb.append(AppConfig.Q);
        sb.append(city);
        sb.append(AppConfig.DAYS);
        sb.append(10); // maksymalna ilość dni do przodu, później dodać konfiguracje
        return sb.toString();
    }

}
