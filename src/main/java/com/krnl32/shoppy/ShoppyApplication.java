package com.krnl32.shoppy;

import com.krnl32.shoppy.entity.Category;
import com.krnl32.shoppy.entity.Product;
import com.krnl32.shoppy.entity.Profile;
import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.repository.CategoryRepository;
import com.krnl32.shoppy.repository.ProductRepository;
import com.krnl32.shoppy.repository.UserRepository;
import com.krnl32.shoppy.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ShoppyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthService authService, UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
		return runner -> {
			createUsersAndProfiles(userRepository);
			createCategoriesAndProducts(categoryRepository);
		};
	}

	private void createUsersAndProfiles(UserRepository userRepository) {
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
	}

	private void createCategoriesAndProducts(CategoryRepository categoryRepository) {
		Category category1 = new Category(null, "Category1", "Category1Desc", null);
		Category category2 = new Category(null, "Category2", "Category2Desc", null);
		Category category3 = new Category(null, "Category3", "Category3Desc", null);

		Product product1 = new Product(null, "Product1", "Product1Desc", 9.99d, 10, null);
		Product product2 = new Product(null, "Product2", "Product2Desc", 59.99d, 35, null);
		Product product3 = new Product(null, "Product3", "Product3Desc", 69.99d, 58, null);
		Product product4 = new Product(null, "Product4", "Product4Desc", 99.99d, 80, null);

		category1.setProducts(List.of(product1, product2));
		product1.setCategory(category1);
		product2.setCategory(category1);

		category2.setProducts(List.of(product3));
		product3.setCategory(category2);

		category3.setProducts(List.of(product4));
		product4.setCategory(category3);

		categoryRepository.saveAll(List.of(category1, category2, category3));
	}
}
