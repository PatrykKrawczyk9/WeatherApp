package pl.kurs.weatherapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_weather_condition")
@NoArgsConstructor
@Data
public class CurrentWeatherCondition implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private LocalDateTime date;
    private double avgTemp;

    public CurrentWeatherCondition(String city, LocalDateTime date, double avgTemp) {
        this.city = city;
        this.date = date;
        this.avgTemp = avgTemp;
    }
}
