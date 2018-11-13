package com.liu.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class OaServericeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OaServericeApplication.class, args);
	}
	
}
