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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void shouldReturnCurrentCorrectTemp() throws InvalidDataException, IOException {
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

        double currentWeatherTemp = service.getCurrentWeatherTemp(city);

        sa.assertThat(currentWeatherTemp).isEqualTo(10);
        sa.assertAll();
    }

    @Test
    @Disabled
    public void shouldReturnForecastCorrectTemp() throws InvalidDataException, IOException {
        String city = "Poznan";
        String exampleUrl = "https://www.example.com";
        URL url = new URL(exampleUrl);
        LocalDate date = LocalDate.of(2024, 4, 30);
        when(urlBuild.buildUrl(city)).thenReturn(exampleUrl);

        JsonNode rootNode = mock(JsonNode.class);
        JsonNode forecastNode = mock(JsonNode.class);
        JsonNode daysArrayNode = mock(JsonNode.class); // Reprezentuje listę dni
        JsonNode dayNode = mock(JsonNode.class);
        JsonNode tempNode = mock(JsonNode.class);

        // Ustawienie URL
        when(urlBuild.buildUrl(city)).thenReturn(exampleUrl);

        // Konfiguracja ObjectMapper
        when(objectMapper.readTree(new URL(exampleUrl))).thenReturn(rootNode);
        when(rootNode.get("forecast")).thenReturn(forecastNode);
        when(forecastNode.get("forecastday")).thenReturn(daysArrayNode);

        // Symulacja iteratora
        List<JsonNode> daysList = Arrays.asList(dayNode);
        when(daysArrayNode.elements()).thenReturn(daysList.iterator());

        // Konfiguracja dnia, który pasuje do podanej daty
        when(dayNode.get("date")).thenReturn(mock(JsonNode.class));
        when(dayNode.get("date").asText()).thenReturn(date.toString());
        when(dayNode.get("day")).thenReturn(tempNode);
        when(tempNode.get("avgtemp_c")).thenReturn(mock(JsonNode.class));
        when(tempNode.get("avgtemp_c").asDouble()).thenReturn(15.5);

        // Wywołanie metody i asercje
        double forecastWeather = service.getForecastWeather(city, date);
        assertEquals(15.5, forecastWeather, 0.0);


        JsonNode temp4NodeMock = mock(JsonNode.class);
        when(temp4NodeMock.asDouble()).thenReturn(10.0);

        JsonNode temp3NodeMock = mock(JsonNode.class);
        when(temp3NodeMock.get("avgtemp_c")).thenReturn(temp4NodeMock);

        JsonNode temp2NodeMock = mock(JsonNode.class);
        when(temp2NodeMock.get("day")).thenReturn(temp3NodeMock);

        JsonNode temp1NodeMock = mock(JsonNode.class);
        when(temp1NodeMock.get("date")).thenReturn(temp2NodeMock);

        JsonNode resultNodeMock = mock(JsonNode.class);

        JsonNode rootNodeMock = mock(JsonNode.class);
        when(rootNodeMock.get("forecast")).thenReturn(resultNodeMock);

        when(urlBuild.buildUrl(city)).thenReturn(exampleUrl);
//        when(objectMapper.readTree(url)).thenReturn(jsonNode);

    }

    @Test
    public void shouldThrowInvalidDataExceptionWhenCityIsNull() {
        String city = "";

        Exception e = assertThrows(InvalidDataException.class, () -> service.getCurrentWeatherTemp(city));

        sa.assertThat(e).hasCauseExactlyInstanceOf(MalformedURLException.class);
        sa.assertThat(e).hasMessage("Niepoprawnie podana miejscowosc");
        sa.assertThat(e).isExactlyInstanceOf(InvalidDataException.class);
        sa.assertAll();
    }
}