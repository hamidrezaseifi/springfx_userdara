package com.seifi.springfx_userdara.repositories;

import com.seifi.springfx_userdara.entities.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
}
