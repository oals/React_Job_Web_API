package com.example.jobx_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.jobx_api.dao")
public class JobxApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobxApiApplication.class, args);
	}

}
