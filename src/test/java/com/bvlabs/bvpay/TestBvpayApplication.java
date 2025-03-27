package com.bvlabs.bvpay;

import org.springframework.boot.SpringApplication;

public class TestBvpayApplication {

	public static void main(String[] args) {
		SpringApplication.from(BvpayApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
