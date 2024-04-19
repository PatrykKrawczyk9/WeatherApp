package pl.kurs.weatherapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "current_weather_condition")
public class CurrentWeatherCondition implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue
    private long id;
    private String city;
    private LocalDateTime date;
    private double avgTemp;

    public CurrentWeatherCondition() {
    }

    public CurrentWeatherCondition(String city, LocalDateTime date, double avgTemp) {
        this.city = city;
        this.date = date;
        this.avgTemp = avgTemp;
    }

    public CurrentWeatherCondition(long id, String city, LocalDateTime date, double avgTemp) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
        CurrentWeatherCondition that = (CurrentWeatherCondition) o;
        return id == that.id && Double.compare(that.avgTemp, avgTemp) == 0 && Objects.equals(city, that.city) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, date, avgTemp);
    }

    @Override
    public String toString() {
        return "CurrentWeatherCondition{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", avgTemp=" + avgTemp +
                '}';
    }
}
