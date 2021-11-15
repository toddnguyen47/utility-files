package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public final class ObjectReaderWriterFactory {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ObjectReaderWriterFactory() {}

    public static ObjectReader createObjectReader() {
        return OBJECT_MAPPER.readerFor(JsonNode.class);
    }

    public static ObjectWriter createObjectWriter() {
        return OBJECT_MAPPER.writer();
    }
}
