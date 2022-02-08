package com.seifi.springfx_userdara.services;

import java.util.List;

public interface IWeatherService {
    void initialize();
    String getTestWeatherForecast();
    String getWeatherForecastByLocation(String location) throws Exception;
    List<String> getLocationList();
}
