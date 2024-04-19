package pl.kurs.weatherapp.config;

public interface AppConfig {

    //http://api.weatherapi.com/v1/current.json?key=4fd68a2ba0ec4a8aac875359240104&q=London
    //http://api.weatherapi.com/v1/forecast.json?key=4fd68a2ba0ec4a8aac875359240104&q=Poznan&days=10

    String API_KEY = "4fd68a2ba0ec4a8aac875359240104";
    String CURRENT_BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";
    String FORECAST_BASE_URL = "http://api.weatherapi.com/v1/forecast.json?key=";
    String Q = "&q=";
    String DAYS = "&days=";

}
