package com.objectmapperhelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ObjectMapperConfigTest {

    private ObjectMapperConfig objectMapperConfig;

    @Before
    public void setUp() {
        this.objectMapperConfig = new ObjectMapperConfig();
    }

    @Test
    public void testObjectMapperCreation() {
        final com.fasterxml.jackson.databind.ObjectMapper objectMapper = objectMapperConfig.createObjectMapper();

        Assert.assertEquals(com.fasterxml.jackson.databind.ObjectMapper.class, objectMapper.getClass());
    }
}
