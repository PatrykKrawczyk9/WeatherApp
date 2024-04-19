package pl.kurs.weatherapp.dao;

import pl.kurs.weatherapp.models.CurrentWeatherCondition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurrentWeatherConditionDao implements ICurrentWeatherConditionDao {

    private final DataSource dataSource;

    public CurrentWeatherConditionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(CurrentWeatherCondition weatherCondition) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement prepStm = conn.prepareStatement("INSERT INTO current_weather_condition (city, local_date_time, avg_temp) VALUES (?, ?, ?)");
        ) {
            prepStm.setString(1, weatherCondition.getCity());
            prepStm.setObject(2, weatherCondition.getDate());
            prepStm.setDouble(3, weatherCondition.getAvgTemp());
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
