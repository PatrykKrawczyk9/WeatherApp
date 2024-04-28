package pl.kurs.weatherapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "forecast_weather_condition")
public class ForecastWeatherCondition implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue
    private long id;
    private String city;
    private LocalDate date;
    private double avgTemp;

    public ForecastWeatherCondition() {
    }

    public ForecastWeatherCondition(String city, LocalDate date, double avgTemp) {
        this.city = city;
        this.date = date;
        this.avgTemp = avgTemp;
    }

    public ForecastWeatherCondition(long id, String city, LocalDate date, double avgTemp) {
        this.id = id;
        this.city = city;
        this.date = date;
        this.avgTemp = avgTemp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastWeatherCondition that = (ForecastWeatherCondition) o;
        return id == that.id && Double.compare(that.avgTemp, avgTemp) == 0 && Objects.equals(city, that.city) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, date, avgTemp);
    }

    @Override
    public String toString() {
        return "ForecastWeatherCondition{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", avgTemp=" + avgTemp +
                '}';
    }
}
