package com.soft.bootdemo1;

import com.soft.bootdemo1.dao.BookRepository;
import com.soft.bootdemo1.dao.UserRepository;
import com.soft.bootdemo1.model.Book;
import com.soft.bootdemo1.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@SpringBootApplication
public class Bootdemo1Application {

	public static void main(String[] args) {
		System.out.println("before Main");
		SpringApplication.run(Bootdemo1Application.class, args);
		System.out.println("After Main");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/*@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
						repository.save(new User("ajay", "ajay", "ADMIN_ROLE", true));
		};
	}*/
}
