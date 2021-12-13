package br.com.letscode.starwarsnetwork.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperProvider {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
