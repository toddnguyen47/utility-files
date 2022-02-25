package testutils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <p>
 * Note that the "root" folder to look for resource files are in {@code src/test/resources}
 * </p>
 */
public class ReadFromTestFilesHelper {

    private static final String FILE_NAME_NOT_FOUND = "file name not found: '%s'\n";
    private final ObjectReader objectReader;

    public ReadFromTestFilesHelper(final ObjectReader objectReader) {
        this.objectReader = objectReader;
    }

    public JsonNode readIntoJsonNode(final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        final String str = readFileAsString(filename);
        final JsonNode jsonNode = objectReader.readValue(str);
        return jsonNode;
    }

    public ArrayNode readIntoArrayNode(final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        final JsonNode jsonNode = readIntoJsonNode(filename);
        return (ArrayNode) jsonNode;
    }

    public ObjectNode readIntoObjectNode(final String filename)
            throws JsonParseException, JsonMappingException, IOException {
        final JsonNode jsonNode = readIntoJsonNode(filename);
        return (ObjectNode) jsonNode;
    }

    public String readFileAsString(final String filename) throws IOException {
        String string = "";
        try {
            final URL url = getUrlOfFilename(filename);
            if (url == null) {
                throw new IllegalArgumentException();
            }
            final Path path = Paths.get(url.getFile());
            string = new String(Files.readAllBytes(path));
        } catch (final IllegalArgumentException exception) {
            System.out.printf(FILE_NAME_NOT_FOUND, filename); // NOPMD
            throw exception;
        }
        return string;
    }

    private URL getUrlOfFilename(final String filename) {
        return Thread.currentThread().getContextClassLoader().getResource(filename);
    }
}
