package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
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
    public static ObjectReader createObjectReader(final Class<?> clazz) {
        return OBJECT_MAPPER.readerFor(clazz);
    }

    /**
     * Create ObjectReader for a specific TypeReference
     *
     * @return
     */
    public static ObjectReader createObjectReader(final TypeReference<?> type) {
        return OBJECT_MAPPER.readerFor(type);
    }

    /**
     * Create a default ObjectWriter
     *
     * @return
     */
    public static ObjectWriter createObjectWriter() {
        return OBJECT_MAPPER.writer();
    }

    /**
     * Get a <code>JsonNodeFactory</code> that can be used to create <code>ArrayNode</code> and
     * <code>ObjectNode</code>
     *
     * @return
     */
    public static JsonNodeFactory getNodeFactory() {
        return OBJECT_MAPPER.getNodeFactory();
    }
}
