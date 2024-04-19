package pl.kurs.weatherapp.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.kurs.weatherapp.config.BeansConfig;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeansConfig.class);

        WeatherController controller = ctx.getBean(WeatherController.class);

        controller.mainLoop();

    }
}
