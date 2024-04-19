package pl.kurs.weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kurs.weatherapp.exceptions.InvalidDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherApiServiceTest {

    @Mock
    ObjectMapper objectMapper;
    @Mock
    IUrlBuild urlBuild;
    @InjectMocks
    WeatherApiService service;
    SoftAssertions sa;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        sa = new SoftAssertions();
    }

    @Test
    public void shouldReturnCorrectTemp() throws InvalidDataException, IOException {
        String city = "Poznan";
        String exampleUrl = "https://www.example.com";

        JsonNode tempNodeMock = mock(JsonNode.class);
        when(tempNodeMock.asText()).thenReturn("10");

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
    public void shouldThrowInvalidDataExceptionWhenCityIsNull() {
        String city = "";

        Exception e = assertThrows(InvalidDataException.class, () -> service.getCurrentWeatherTemp(city));

        sa.assertThat(e).hasCauseExactlyInstanceOf(MalformedURLException.class);
        sa.assertThat(e).hasMessage("Niepoprawnie podana miejscowosc");
        sa.assertThat(e).isExactlyInstanceOf(InvalidDataException.class);
        sa.assertAll();
    }
}