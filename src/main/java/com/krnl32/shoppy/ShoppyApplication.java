package com.krnl32.shoppy;

import com.krnl32.shoppy.entity.Profile;
import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.repository.UserRepository;
import com.krnl32.shoppy.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ShoppyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthService authService, UserRepository userRepository) {
		return runner -> {
//			authService.registerUser(new UserRegisterRequestDTO("user1@users.com", "user1", "pass1234"));
//			authService.registerUser(new UserRegisterRequestDTO("user2@users.com", "user2", "pass1234"));
//			authService.registerUser(new UserRegisterRequestDTO("user3@users.com", "user3", "pass1234"));

			User user1 = new User(null, "user1@users.com", "user1", "pass1234", null);
			Profile profile1 = new Profile(null, "first1", "last1", LocalDate.now(), "1234", "Home", "None", user1);
			user1.setProfile(profile1);

			User user2 = new User(null, "user2@users.com", "user2", "pass1234", null);
			Profile profile2 = new Profile(null, "first2", "last2", LocalDate.now(), "1234", "Home", "None", user2);
			user2.setProfile(profile2);

			User user3 = new User(null, "user3@users.com", "user3", "pass1234", null);
			Profile profile3 = new Profile(null, "first3", "last3", LocalDate.now(), "1234", "Home", "None", user3);
			user3.setProfile(profile3);

			User user4 = new User(null, "user4@users.com", "user4", "pass1234", null);
			User user5 = new User(null, "user5@users.com", "user5", "pass1234", null);
			User user6 = new User(null, "user6@users.com", "user6", "pass1234", null);
			User user7 = new User(null, "user7@users.com", "user7", "pass1234", null);

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);
			userRepository.save(user6);
			userRepository.save(user7);
		};
	}
}
