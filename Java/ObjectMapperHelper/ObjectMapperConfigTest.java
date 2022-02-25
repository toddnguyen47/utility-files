package com.objectmapperhelper;

import com.att.eg.schedule.event.config.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		final ObjectMapper objectMapper = objectMapperConfig.createObjectMapper();

		Assert.assertEquals(ObjectMapper.class, objectMapper.getClass());
	}
}
