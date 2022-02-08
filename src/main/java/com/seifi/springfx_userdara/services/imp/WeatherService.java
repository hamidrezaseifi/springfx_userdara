package com.seifi.springfx_userdara.services.imp;

import com.seifi.springfx_userdara.services.IWeatherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService implements IWeatherService {
    @Override
    public String getTestWeatherForecast() {
        return "It's gonna snow a lot. Brace yourselves, the winter is coming.";
    }

    @Override
    public String getWeatherForecastByLocation(String location) {
        return String.format("It's raining in %s.", location);
    }

    @Override
    public List<String> getLocationList() {
        return null;
    }
}
