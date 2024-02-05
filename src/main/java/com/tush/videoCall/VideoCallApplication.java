package com.tush.videoCall;

import com.tush.videoCall.user.User;
import com.tush.videoCall.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VideoCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoCallApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService service) {
		return args -> {
			service.register(User.builder()
					.username("tush")
					.email("tush@gmail.com")
					.password("123")
					.build());

			service.register(User.builder()
					.username("rohan")
					.email("rohan@gmail.com")
					.password("123")
					.build());

			service.register(User.builder()
					.username("aman")
					.email("aman@gmail.com")
					.password("123")
					.build());

			service.register(User.builder()
					.username("bist")
					.email("bist@gmail.com")
					.password("123")
					.build());
		};
	}
}
