package com.objectmapperhelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public com.fasterxml.jackson.databind.ObjectMapper createObjectMapper() {
        return new com.fasterxml.jackson.databind.ObjectMapper();
    }

    @Bean
    public com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper createCouchbaseObjectMapper() {
        return new com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper();
    }
}
