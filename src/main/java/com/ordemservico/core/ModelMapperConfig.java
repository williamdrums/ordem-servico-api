package com.ordemservico.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean //instancia e inicializa e configura um bean do tipo model mapper
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
