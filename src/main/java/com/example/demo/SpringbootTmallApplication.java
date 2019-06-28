package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.example.demo.util.PortUtil;

@SpringBootApplication
@EnableCaching
public class SpringbootTmallApplication {
	static {
        PortUtil.checkPort(6379,"Redis 服务端",true);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTmallApplication.class, args);
	}

}
