package com.example.task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.task.model.User;
import com.example.task.repository.UserRepository;

@SpringBootApplication
public class TaskApplication {

	@Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(1, "user1", "$2a$12$qRe1Gt6J1AHaqS03WSAni.zdXnxCzcE2qVH1vCz1dEv.RekuZKAA2"),
				new User(2, "user2", "$2a$12$B4NAFdgyQI3bFHe2qgsLHO3dSU3LNY5DCIjS0BX9S4QRkyVKnjeWe"),
				new User(3, "user3", "$2a$12$1cEIDHra1DLcoDhoNIP09OCdQEb7WgvdfEXt/TRwa.yjoW3fvndKq")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
