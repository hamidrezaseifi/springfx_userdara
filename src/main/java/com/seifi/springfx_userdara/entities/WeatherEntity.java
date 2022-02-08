package com.seifi.springfx_userdara.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WeatherEntity {

    @Id
    private String location;
    private String weather;

    public WeatherEntity() {
    }

    public WeatherEntity(String location,
                         String weather) {
        this.location = location;
        this.weather = weather;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
