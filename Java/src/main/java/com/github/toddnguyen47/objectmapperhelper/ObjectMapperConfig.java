package com.github.toddnguyen47.objectmapperhelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

  @Bean
  public com.fasterxml.jackson.databind.ObjectMapper createObjectMapper() {
    return new com.fasterxml.jackson.databind.ObjectMapper();
  }
}
