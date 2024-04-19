package pl.kurs.weatherapp.dao;

import pl.kurs.weatherapp.models.CurrentWeatherCondition;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForecastWeatherConditionDao implements IForecastWeatherConditionDao {

    private final DataSource dataSource;

    public ForecastWeatherConditionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(ForecastWeatherCondition weatherCondition) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement prepStm = conn.prepareStatement("INSERT INTO forecast_weather_condition (city, local_date, avg_temp) VALUES (?, ?, ?)");
        ) {
            prepStm.setString(1, weatherCondition.getCity());
            prepStm.setDate(2, Date.valueOf(weatherCondition.getDate()));
            prepStm.setDouble(3, weatherCondition.getAvgTemp());
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
