package pl.kurs.weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.weatherapp.dao.CurrentWeatherConditionRepository;
import pl.kurs.weatherapp.dao.ForecastWeatherConditionRepository;
import pl.kurs.weatherapp.exceptions.InvalidDataException;
import pl.kurs.weatherapp.models.CurrentWeatherCondition;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WeatherApiServiceTest {

    @Mock
    ObjectMapper objectMapper;
    @Mock
    IUrlBuild urlBuild;
    @Mock
    CurrentWeatherConditionRepository currentWeatherConditionRepository;
    @Mock
    ForecastWeatherConditionRepository forecastWeatherConditionRepository;
    @InjectMocks
    WeatherApiService service;
    SoftAssertions sa;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        sa = new SoftAssertions();
    }

    @Test
    public void getCurrentWeather_ValidData_ReturnCurrentTemperature() throws InvalidDataException, IOException {
        //given
        String city = "Poznan";
        String exampleUrl = "https://www.example.com";

        JsonNode tempNodeMock = mock(JsonNode.class);
        when(tempNodeMock.asDouble()).thenReturn(10.0);

        JsonNode resultNodeMock = mock(JsonNode.class);
        when(resultNodeMock.get("temp_c")).thenReturn(tempNodeMock);

        JsonNode rootNodeMock = mock(JsonNode.class);
        when(rootNodeMock.get("current")).thenReturn(resultNodeMock);

        when(urlBuild.buildUrl(city)).thenReturn(exampleUrl);
        when(objectMapper.readTree(any(URL.class))).thenReturn(rootNodeMock);

        double result = service.getCurrentWeatherTemp(city);

        assertEquals(10.0, result);
        verify(currentWeatherConditionRepository).save(any(CurrentWeatherCondition.class));
    }

    @Test
    void getForecastWeather_ValidData_ReturnsAverageTemperature() throws IOException, InvalidDataException {
        // Given
        String city = "Warsaw";
        LocalDate date = LocalDate.of(2021, 10, 20);
        String url = "http://example.com/api";
        String jsonResponse = "{\"forecast\":{\"forecastday\":[{\"date\":\"2021-10-20\",\"day\":{\"avgtemp_c\":15.5}}]}}";

        when(urlBuild.buildUrl(city)).thenReturn(url);
        when(objectMapper.readTree(new URL(url))).thenReturn(new ObjectMapper().readTree(jsonResponse));

        // When
        double result = service.getForecastWeather(city, date);

        // Then
        assertEquals(15.5, result);
        verify(forecastWeatherConditionRepository).save(any(ForecastWeatherCondition.class));
    }

    @Test
    void getForecastWeather_InvalidCity_ThrowsInvalidDataException() throws IOException {
        String city = "UnknownCity";
        LocalDate date = LocalDate.now();
        String url = "http://example.com/api";

        when(urlBuild.buildUrl(city)).thenReturn(url);
        when(objectMapper.readTree(any(URL.class))).thenThrow(new IOException());

        Exception e = assertThrows(InvalidDataException.class, () -> service.getForecastWeather(city, date));

        sa.assertThat(e).hasCauseExactlyInstanceOf(IOException.class);
        sa.assertThat(e).hasMessage("Niepoprawnie podana miejscowosc");
        sa.assertThat(e).isExactlyInstanceOf(InvalidDataException.class);
        sa.assertAll();
    }

    @Test
    public void getCurrentWeather_InvalidCity_ThrowsInvalidDataException() throws IOException {
        String city = "UnknownCity";
        String url = "http://example.com/api";

        when(urlBuild.buildUrl(city)).thenReturn(url);
        when(objectMapper.readTree(any(URL.class))).thenThrow(new IOException());

        Exception e = assertThrows(InvalidDataException.class, () -> service.getCurrentWeatherTemp(city));

        sa.assertThat(e).hasCauseExactlyInstanceOf(IOException.class);
        sa.assertThat(e).hasMessage("Niepoprawnie podana miejscowosc");
        sa.assertThat(e).isExactlyInstanceOf(InvalidDataException.class);
        sa.assertAll();
    }
}