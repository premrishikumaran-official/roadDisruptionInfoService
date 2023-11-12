package org.intrv.tfl.roaddisruptioninfoservice;

import org.intrv.tfl.roaddisruptioninfoservice.client.api.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = ClientService.class)
public class RoadServiceApplication {
	public static void main(String[] args) {

		SpringApplication.run(RoadServiceApplication.class, args);
	}

}
