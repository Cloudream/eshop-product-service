package com.cloudream.eshop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EshopProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(EshopProductApplication.class, args);
	}
}
