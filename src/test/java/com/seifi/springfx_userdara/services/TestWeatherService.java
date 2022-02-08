package com.seifi.springfx_userdara.services;

import com.seifi.springfx_userdara.entities.UserDataEntity;
import com.seifi.springfx_userdara.entities.WeatherEntity;
import com.seifi.springfx_userdara.repositories.WeatherRepository;
import com.seifi.springfx_userdara.services.imp.UserDataService;
import com.seifi.springfx_userdara.services.imp.WeatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestWeatherService {

    private IWeatherService weatherService;

    @Mock
    private WeatherRepository weatherRepository;

    List<WeatherEntity> testWeatherItems;


    private String testLocation = null;
    private String testLocationFalse = "testLocationFalse";

    private String testWeather = null;


    public TestWeatherService() {
        testWeatherItems = new ArrayList<>();
        for(int i=1; i<5; i++){
            String location = String.format("Location-%d", i);
            String weather = String.format("Weather-%d", i);

            testLocation = testLocation == null ? location : testLocation;
            testWeather = testWeather == null ? weather : testWeather;

            testWeatherItems.add(new WeatherEntity(location, weather));
        }
    }

    @Before
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this);

        Mockito.when(weatherRepository.findAll()).thenReturn(this.testWeatherItems);

        weatherService = new WeatherService(weatherRepository);
        weatherService.initialize();
    }

    @Test
    public void TestGetTestWeatherForecast() {

        String resultInfo = this.weatherService.getTestWeatherForecast();

        Assert.assertNotNull("The result of getTestWeatherForecast must not be null", resultInfo);
        Assert.assertEquals("The result of getTestWeatherForecast must not be " + WeatherService.TEST_WAETHER_INO,
                            WeatherService.TEST_WAETHER_INO,
                            resultInfo);

    }

    @Test
    public void TestGetWeatherForecastByLocation() throws Exception {

        String resultWeather = this.weatherService.getWeatherForecastByLocation(testLocation);

        Assert.assertNotNull("The result of getWeatherForecastByLocation must not be null", resultWeather);
        Assert.assertEquals("The result of getWeatherForecastByLocation must not be " + testWeather,
                            testWeather,
                            resultWeather);

    }

    @Test
    public void TestGetWeatherForecastByFalseLocation() throws Exception {

        String testFalseWeatherMessage = String.format("There's no weather information about %s.", testLocationFalse);

        Exception exception = assertThrows(Exception.class, () -> {
            this.weatherService.getWeatherForecastByLocation(testLocationFalse);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw " + testFalseWeatherMessage,
                            testFalseWeatherMessage,
                            exception.getLocalizedMessage());
    }

    @Test
    public void TestGetLocationList() throws Exception {

        List<String> resultList = this.weatherService.getLocationList();

        Assert.assertNotNull("The result of getLocationList must not be null", resultList);
        Assert.assertEquals("The result of getLocationList must have " + testWeatherItems.size() + " items",
                            testWeatherItems.size(),
                            resultList.size());

        for(WeatherEntity item: testWeatherItems){
            Assert.assertTrue(String.format("The %s must be in restlt list", item.getLocation()),
                              resultList.contains(item.getLocation()));
        }

    }


}
