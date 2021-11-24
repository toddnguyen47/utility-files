package com.utils;

import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ObjectReaderWriterFactoryTest {
    @Test
    public void test_GivenObjectReaderCreated_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass()
            throws Exception {
        final ObjectReader objectReader = ObjectReaderWriterFactory.createObjectReader();

        final JsonNode jsonNode = objectReader.readValue("{}");

        Assert.assertEquals("class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
        Assert.assertNotNull(jsonNode);
    }

    @Test
    public void test_GivenObjectReaderCreatedWithClass_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass()
            throws Exception {
        final ObjectReader objectReader = ObjectReaderWriterFactory.createObjectReader(Map.class);

        final Map<String, Object> jsonNode = objectReader.readValue("{}");

        Assert.assertEquals("class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
        Assert.assertNotNull(jsonNode);
    }

    @Test
    public void test_GivenObjectWriterCreated_WhenCallingFactoryMethod_ThenInstanceIsOfObjectWriterClass() {
        final ObjectWriter objectWriter = ObjectReaderWriterFactory.createObjectWriter();

        Assert.assertEquals("class com.fasterxml.jackson.databind.ObjectWriter", objectWriter.getClass().toString());
    }

    @Test
    public void test_GivenValidJsonNodeFactory_WhenGettingJsonNodeFactory_ThenInstanceIsOfJsonNodeFactoryClass() {
        final JsonNodeFactory jsonNodeFactory = ObjectReaderWriterFactory.getNodeFactory();

        Assert.assertEquals("class com.fasterxml.jackson.databind.node.JsonNodeFactory",
                jsonNodeFactory.getClass().toString());
    }
}
