package testutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <p>
 * Note that the "root" folder to look for resource files are in <code>src/test/resources</code>
 * </p>
 */
public final class ReadFromTestFilesHelper {

    private static final String FILE_NAME_NOT_FOUND = "file name not found: '%s'\n";

    // Private constructor to prevent instantiation
    private ReadFromTestFilesHelper() {}

    public static JsonNode readIntoJsonNode(final ObjectReader objectReader, final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        JsonNode jsonNode = objectReader.createObjectNode();
        try {
            jsonNode = objectReader.readValue(Thread.currentThread().getContextClassLoader().getResource(filename),
                    JsonNode.class);
        } catch (IllegalArgumentException exception) {
            System.out.printf(FILE_NAME_NOT_FOUND, filename); // NOPMD
            throw exception;
        }
        return jsonNode;
    }

    public static ArrayNode readIntoArrayNode(final ObjectReader objectReader, final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        ArrayNode arrayNode = (ArrayNode) objectReader.createArrayNode();
        try {
            arrayNode = objectReader.readValue(Thread.currentThread().getContextClassLoader().getResource(filename),
                    ArrayNode.class);
        } catch (IllegalArgumentException exception) {
            System.out.printf(FILE_NAME_NOT_FOUND, filename); // NOPMD
            throw exception;
        }
        return arrayNode;
    }

    public static ObjectNode readIntoObjectNode(final ObjectReader objectReader, final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectNode objectNode = (ObjectNode) objectReader.createObjectNode();
        try {
            objectNode = objectReader.readValue(Thread.currentThread().getContextClassLoader().getResource(filename),
                    ObjectNode.class);
        } catch (IllegalArgumentException exception) {
            System.out.printf(FILE_NAME_NOT_FOUND, filename); // NOPMD
            throw exception;
        }
        return objectNode;
    }

    public static String readFileAsString(final String filename) throws IOException {
        String string = "";
        try {
            string = new String(Files.readAllBytes(
                    Paths.get(Thread.currentThread().getContextClassLoader().getResource(filename).getFile())));
        } catch (IllegalArgumentException exception) {
            System.out.printf(FILE_NAME_NOT_FOUND, filename); // NOPMD
            throw exception;
        }
        return string;
    }
}
