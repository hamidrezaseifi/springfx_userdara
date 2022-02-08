package com.seifi.springfx_userdara.services;

import com.seifi.springfx_userdara.entities.UserDataEntity;

import java.util.List;

public interface IUserDataService {

    String getUserAdditionInfo(String name, int age, String location);

    UserDataEntity insert(String name, int age, String location);

    List<UserDataEntity> getSortedUserList();
}
