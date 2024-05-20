package pl.kurs.weatherapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "forecast_weather_condition")
@NoArgsConstructor
@Data
public class ForecastWeatherCondition implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue
    private long id;
    private String city;
    private LocalDate date;
    private double avgTemp;

    public ForecastWeatherCondition(String city, LocalDate date, double avgTemp) {
        this.city = city;
        this.date = date;
        this.avgTemp = avgTemp;
    }

}
