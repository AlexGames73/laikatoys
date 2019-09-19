package com.tripplea.laikatoys;

import com.tripplea.laikatoys.core.repository.JpaDetachableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaDetachableRepositoryImpl.class)
public class LaikatoysApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaikatoysApplication.class, args);
	}

}
