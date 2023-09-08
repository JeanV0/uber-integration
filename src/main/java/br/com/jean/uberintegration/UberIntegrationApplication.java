package br.com.jean.uberintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class UberIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberIntegrationApplication.class, args);
	}

}
