package com.seifi.springfx_userdara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableAutoConfiguration
@EntityScan("com.seifi.springfx_userdara.entities")
@SpringBootApplication
public class SpringfxUserdaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataJavaFxApplication.class, args);
	}

}
