package com.example.BootJPABoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class BootJpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJpaBoardApplication.class, args);
	}

}
