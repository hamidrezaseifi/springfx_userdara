package com.seifi.springfx_userdara.repositories;

import com.seifi.springfx_userdara.entities.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataEntity, Long> {

    List<UserDataEntity> findByOrderByIdDesc();
}
