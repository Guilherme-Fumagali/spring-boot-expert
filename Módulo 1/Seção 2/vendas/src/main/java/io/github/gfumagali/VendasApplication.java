package io.github.gfumagali;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gfumagali.annotations.*;

@SpringBootApplication
@RestController
public class VendasApplication {
	
	@Today
	private String dateNow;

	@Value("${application.name}")
	private String applicationName;

	@GetMapping("/hello")
	public String helloWorld() {
		return applicationName + " --- " + dateNow;
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
