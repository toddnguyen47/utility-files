package com.utils;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.Assert;
import org.junit.Test;

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
    public void test_GivenObjectReaderCreatedWithTypeReference_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass()
            throws Exception {
        final ObjectReader objectReader = ObjectReaderWriterFactory.createObjectReader(
            new TypeReference<List<Integer>>() {}
        );

        final List<Integer> list1 = objectReader.readValue("[2, 5]");

        Assert.assertEquals("class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
        Assert.assertNotNull(list1);
        Assert.assertEquals(Integer.valueOf(2), list1.get(0));
        Assert.assertEquals(Integer.valueOf(5), list1.get(1));
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
