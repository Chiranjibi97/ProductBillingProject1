package com.nt;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductBillingProject1Application {

	private static Date date=new Date();
	public static void main(String[] args) {
		SpringApplication.run(ProductBillingProject1Application.class, args);
		System.out.println("Current date is : "+date);
	}

}
