package com.github.toddnguyen47.objectmapperhelper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class ObjectMapperHelperTest {

    @Test
    public void
            test_GivenObjectReaderCreated_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass()
                    throws Exception {
        final ObjectReader objectReader = ObjectMapperHelper.createObjectReader();

        final JsonNode jsonNode = objectReader.readValue("{}");

        Assert.assertEquals(
                "class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
        Assert.assertNotNull(jsonNode);
    }

    @Test
    public void
            test_GivenObjectReaderCreatedWithClass_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass()
                    throws Exception {
        final ObjectReader objectReader = ObjectMapperHelper.createObjectReader(Map.class);

        final Map<String, Object> jsonNode = objectReader.readValue("{}");

        Assert.assertEquals(
                "class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
        Assert.assertNotNull(jsonNode);
    }

    @Test
    public void
            test_GivenObjectReaderCreatedWithTypeReference_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass()
                    throws Exception {
        final ObjectReader objectReader =
                ObjectMapperHelper.createObjectReader(new TypeReference<List<Integer>>() {});

        final List<Integer> list1 = objectReader.readValue("[2, 5]");

        Assert.assertEquals(
                "class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
        Assert.assertNotNull(list1);
        Assert.assertEquals(Integer.valueOf(2), list1.get(0));
        Assert.assertEquals(Integer.valueOf(5), list1.get(1));
    }

    @Test
    public void
            test_GivenObjectWriterCreated_WhenCallingFactoryMethod_ThenInstanceIsOfObjectWriterClass() {
        final ObjectWriter objectWriter = ObjectMapperHelper.createObjectWriter();

        Assert.assertEquals(
                "class com.fasterxml.jackson.databind.ObjectWriter", objectWriter.getClass().toString());
    }

    @Test
    public void
            test_GivenValidJsonNodeFactory_WhenGettingJsonNodeFactory_ThenInstanceIsOfJsonNodeFactoryClass() {
        final JsonNodeFactory jsonNodeFactory = ObjectMapperHelper.getNodeFactory();

        Assert.assertEquals(
                "class com.fasterxml.jackson.databind.node.JsonNodeFactory",
                jsonNodeFactory.getClass().toString());
    }

    @Test
    public void test_GivenMap_WhenConvertingToJsonNode_ThenJsonNodeIsCorrect() {
        final Map<String, String> map1 = new HashMap<>();
        map1.put("Hello", "World");
        map1.put("Meaning of Life", "42");

        final JsonNode actualJsonNode = ObjectMapperHelper.valueToTree(map1);

        final String expectedStr = "{\"Hello\":\"World\",\"Meaning of Life\":\"42\"}";
        Assert.assertEquals(expectedStr, actualJsonNode.toString());
    }

    @Test
    public void test_GivenGetObjectMapper_ThenReturnCorrectMapper() {
        final ObjectMapper objectMapper = ObjectMapperHelper.getObjectMapper();

        Assert.assertEquals(ObjectMapper.class, objectMapper.getClass());
    }
}
