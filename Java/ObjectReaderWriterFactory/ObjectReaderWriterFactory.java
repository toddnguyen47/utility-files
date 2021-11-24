package com.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public final class ObjectReaderWriterFactory {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ObjectReaderWriterFactory() {}

    /**
     * Create ObjectReader for the `JsonNode` class
     *
     * @return
     */
    public static ObjectReader createObjectReader() {
        return OBJECT_MAPPER.readerFor(JsonNode.class);
    }

    /**
     * Create ObjectReader for a specific class
     *
     * @return
     */
    public static ObjectReader createObjectReader(Class<?> clazz) {
        return OBJECT_MAPPER.readerFor(clazz);
    }

    /**
     * Create a default ObjectWriter
     *
     * @return
     */
    public static ObjectWriter createObjectWriter() {
        return OBJECT_MAPPER.writer();
    }

    public static JsonNodeFactory getNodeFactory() {
        return OBJECT_MAPPER.getNodeFactory();
    }
}
