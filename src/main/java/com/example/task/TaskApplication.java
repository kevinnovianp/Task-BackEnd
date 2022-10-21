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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.task.config.SecurityConfig;
import com.example.task.model.Meeting;
import com.example.task.model.User;
import com.example.task.repository.MeetingRepository;
import com.example.task.repository.UserRepository;
import com.example.task.service.MeetingService;

@SpringBootApplication
public class TaskApplication {

	@Autowired
    private UserRepository userRepository;
	@Autowired
	MeetingService meetingService;
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(1, "user1", bCryptPasswordEncoder.encode("user1").toString()),
				new User(2, "user2", bCryptPasswordEncoder.encode("user2").toString()),
				new User(3, "user3", bCryptPasswordEncoder.encode("user3").toString())
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

	@PostConstruct
    public void initMeetings() {
		meetingService.addMeeting(new Meeting(1, "10:00", "11:30", "2022-01-11", "Meet 1", "This is first meet", 1));
		meetingService.addMeeting(new Meeting(2, "11:00", "11:30", "2022-08-12", "Urgent Meet", "This is second meet", 1));
		meetingService.addMeeting(new Meeting(3, "12:00", "13:30", "2022-01-13", "Meet 3", "This is third meet", 1));
		meetingService.addMeeting(new Meeting(4, "15:00", "16:30", "2022-01-14", "Meet 4", "This is fourth meet", 2));
		meetingService.addMeeting(new Meeting(5, "10:00", "13:30", "2022-01-13", "Meet 5", "This is fifth meet", 2));
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
