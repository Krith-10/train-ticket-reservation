package com.krith.reservation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
		info = @Info(
				title = "Ticket Reservation Service",
				version = "1.0",
				description = "API Service that perform train ticket reservation and related operation"
		)
)
@EntityScan(basePackages = {"com.krith.reservation.entity"})
@EnableJpaRepositories(basePackages = {"com.krith.reservation.repository"})
@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

}
