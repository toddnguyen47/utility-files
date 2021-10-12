package testutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <p>
 * Note that the "root" folder to look for resource files are in
 * <code>src/test/resources</code>
 * </p>
 */
public final class ReadFromTestFilesHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Private constructor to prevent instantiation
    private ReadFromTestFilesHelper() {}

    public static JsonNode readIntoJsonNode(final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        return OBJECT_MAPPER.readValue(Thread.currentThread().getContextClassLoader().getResource(filename),
                JsonNode.class);
    }

    public static ArrayNode readIntoArrayNode(final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        return OBJECT_MAPPER.readValue(Thread.currentThread().getContextClassLoader().getResource(filename),
                ArrayNode.class);
    }

    public static ObjectNode readIntoObjectNode(final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        return OBJECT_MAPPER.readValue(Thread.currentThread().getContextClassLoader().getResource(filename),
                ObjectNode.class);
    }

    public static String readFileAsString(final String fileName) throws IOException {
        return new String(Files.readAllBytes(
                Paths.get(Thread.currentThread().getContextClassLoader().getResource(fileName).getFile())));
    }
}
