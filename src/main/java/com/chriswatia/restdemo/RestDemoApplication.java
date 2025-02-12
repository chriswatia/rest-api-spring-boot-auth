package com.chriswatia.restdemo;

import com.chriswatia.restdemo.config.RsaKeyProperties;
import com.chriswatia.restdemo.model.CloudVendor;
import com.chriswatia.restdemo.model.User;
import com.chriswatia.restdemo.repository.UsersRepository;
import com.chriswatia.restdemo.service.CloudVendorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CloudVendorService cloudVendorService, UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			CloudVendor cloudVendor = new CloudVendor(1L, "AWS", "Seattle", "1234567890");
			User user = new User("user", passwordEncoder.encode("user"), "ROLE_USER");
			User user1 = new User("admin", passwordEncoder.encode("admin"), "ROLE_USER, ROLE_ADMIN");
			usersRepository.save(user);
			usersRepository.save(user1);
			cloudVendorService.createCloudVendor(cloudVendor);
			System.out.println("Hello Spring Boot");
		};
	}

}

