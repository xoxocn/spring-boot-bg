package com.xoxo.bgms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xoxo.bgms.mapper")
public class BgmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgmsApplication.class, args);
	}

}

