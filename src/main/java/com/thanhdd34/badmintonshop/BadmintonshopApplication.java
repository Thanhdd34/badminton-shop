package com.thanhdd34.badmintonshop;

import com.thanhdd34.badmintonshop.entity.User;
import com.thanhdd34.badmintonshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BadmintonshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadmintonshopApplication.class, args);
	}

}
