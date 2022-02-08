package com.seifi.springfx_userdara.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String location;

    public UserDataEntity() {
    }

    public UserDataEntity(Long id,
                          String name,
                          Integer age,
                          String location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public UserDataEntity(String name,
                          Integer age,
                          String location) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle(){
        return String.format("%s (age: %d) at %s: id:%d", this.name, this.age, this.location, this.id);
    }

}
