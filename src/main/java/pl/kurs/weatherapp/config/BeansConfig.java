package pl.kurs.weatherapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import pl.kurs.weatherapp.utils.ObjectMapperHolder;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "pl.kurs.weatherapp")
public class BeansConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return ObjectMapperHolder.INSTANCE.getObjectMapper();
    }

    @Bean
    public LocalEntityManagerFactoryBean createEmf() {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("myPersistenceUnit");
        return emf;
    }
}
