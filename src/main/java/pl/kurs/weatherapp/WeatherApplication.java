package pl.kurs.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.weatherapp.app.WeatherController;

@SpringBootApplication
public class WeatherApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(WeatherApplication.class);

        WeatherController controller = ctx.getBean(WeatherController.class);

        controller.mainLoop();

    }
}
