package com.seifi.springfx_userdara.services.imp;

import com.seifi.springfx_userdara.entities.WeatherEntity;
import com.seifi.springfx_userdara.repositories.WeatherRepository;
import com.seifi.springfx_userdara.services.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherService implements IWeatherService {

    private WeatherRepository weatherRepository;
    private Map<String, String> weathers;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @PostConstruct
    public void initialize(){
        List<WeatherEntity> items = this.weatherRepository.findAll();
        if(items.isEmpty()){
            this.weatherRepository.save(new WeatherEntity("Hannover", "Windy"));
            this.weatherRepository.save(new WeatherEntity("Hamburg", "Raining"));
            this.weatherRepository.save(new WeatherEntity("München", "Snowing"));
            this.weatherRepository.save(new WeatherEntity("Frankfurt", "Clear"));

            items = this.weatherRepository.findAll();
        }

        this.weathers = items.stream().collect(Collectors.toMap( w -> w.getLocation(), w -> w.getWeather()));
    }

    @Override
    public String getTestWeatherForecast() {
        return "It's gonna snow a lot. Brace yourselves, the winter is coming.";
    }

    @Override
    public String getWeatherForecastByLocation(String location) throws Exception {
        if(this.weathers.containsKey(location)){
            return this.weathers.get(location);
        }

        throw new Exception(String.format("There's no weather information about %s.", location));
    }

    @Override
    public List<String> getLocationList() {
        return this.weathers.keySet().stream().collect(Collectors.toList());
    }
}
