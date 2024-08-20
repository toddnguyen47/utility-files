package com.github.toddnguyen47.testfileshelpers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 * Note that the "root" folder to look for resource files are in {@code src/test/resources}
 * </p>
 */
public final class ReadFromTestFilesHelper {

  private static final String FILE_NAME_NOT_FOUND = "file name not found: '%s'\n";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private ReadFromTestFilesHelper() {}

  public static JsonNode readIntoJsonNode(final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final String str = readFileAsString(filename);
    final JsonNode jsonNode = OBJECT_MAPPER.readTree(str);
    return jsonNode;
  }

  public static ArrayNode readIntoArrayNode(final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final JsonNode jsonNode = readIntoJsonNode(filename);
    return (ArrayNode) jsonNode;
  }

  public static ObjectNode readIntoObjectNode(final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final JsonNode jsonNode = readIntoJsonNode(filename);
    return (ObjectNode) jsonNode;
  }

  public static String readFileAsString(final String filename) throws IOException {
    byte[] bytes = readFileAsBytes(filename);
    return new String(bytes);
  }

  public static byte[] readFileAsBytes(final String filename) throws IOException {
    final URL url;
    url = getUrlOfFilename(filename);
    if (url == null) {
      throw new IllegalArgumentException();
    }
    final byte[] bytes;
    try {
      final Path path = Paths.get(url.getFile());
      bytes = Files.readAllBytes(path);
    } catch (final IllegalArgumentException exception) {
      System.out.printf(FILE_NAME_NOT_FOUND, filename); // NOPMD
      throw exception;
    }
    return bytes;
  }

  private static URL getUrlOfFilename(final String filename) {
    return Thread.currentThread().getContextClassLoader().getResource(filename);
  }
}
