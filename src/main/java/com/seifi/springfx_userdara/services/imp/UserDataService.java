package com.seifi.springfx_userdara.services.imp;

import com.seifi.springfx_userdara.entities.UserDataEntity;
import com.seifi.springfx_userdara.services.IUserDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService implements IUserDataService {
    @Override
    public String getUserAdditionInfo(String name,
                                      String age,
                                      String location) {
        String weather = "Test Weather";
        return String.format(
                "You are %s (%s years old) and are living at %s. The weather at your location is %s",
                name,
                age,
                location,
                weather);
    }

    @Override
    public UserDataEntity insert(String name,
                                 String age,
                                 String location) {
        return null;
    }

    @Override
    public List<UserDataEntity> getSortedUserList() {
        return null;
    }
}
