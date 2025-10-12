package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

	@Bean
	public ModelMapper modelMapper() {
		// Bean登録することで、DIすることができるようになる
		return new ModelMapper();
	}
}
