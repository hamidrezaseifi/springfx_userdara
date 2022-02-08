package com.seifi.springfx_userdara.services.imp;

import com.seifi.springfx_userdara.entities.UserDataEntity;
import com.seifi.springfx_userdara.repositories.UserDataRepository;
import com.seifi.springfx_userdara.services.IUserDataService;
import com.seifi.springfx_userdara.services.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService implements IUserDataService {

    private UserDataRepository userDataRepository;
    private IWeatherService weatherService;

    @Autowired
    public UserDataService(IWeatherService weatherService,
                           UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
        this.weatherService = weatherService;
    }

    @Override
    public String getUserAdditionInfo(String name,
                                      int age,
                                      String location) {
        try {
            String weather  = this.weatherService.getWeatherForecastByLocation(location);
            return String.format(
                    "You are %s (%d years old) and are living at %s. The weather at your location is %s",
                    name,
                    age,
                    location,
                    weather);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

    }

    @Override
    public UserDataEntity insert(String name,
                                 int age,
                                 String location) {
        return null;
    }

    @Override
    public List<UserDataEntity> getSortedUserList() {
        return this.userDataRepository.findByOrderByIdDesc();
    }
}
