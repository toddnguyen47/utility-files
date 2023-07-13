package com.github.toddnguyen47.objectmapperhelper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <div> Helper class to create `ObjectReader`, `ObjectWriter`, and `JsonNodeFactory`. Also expose
 * some functions of `ObjectMapper`. This is because according to `fasterxml`'s documentation,
 * `ObjectMapper`s are often very heavy; thus, lightweight instances of `ObjectReader` and
 * `ObjectWriter` should be used instead. </div>
 *
 * <div> Code Examples:
 *
 * <pre>
 * // To read a value
 * final ObjectReader objectReader = ObjectMapperHelper.createObjectReader();
 * final JsonNode jsonNode = objectReader.readValue({inputString});
 *
 * // To read into a List<String>
 * final ObjectReader objectReaderList = ObjectMapperHelper.createObjectReader(new TypeReference<List<String>>(){});
 * final List<String> list1 = objectReaderList.readValue({inputString});
 *
 * // To write a POJO as a String
 * final ObjectWriter objectWriter = ObjectMapperHelper.createObjectWriter();
 * final String str1 = objectWriter.writeValueAsString({inputJsonSerializableObject});
 *
 * // To convert object to JsonNode
 * final JsonNode jsonNode = ObjectMapperHelper.convertObjectToJsonNode({inputObject});
 * </pre>
 *
 * </div>
 */
public final class ObjectMapperHelper {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private ObjectMapperHelper() {}

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

  /**
   * See {@link ObjectMapper#valueToTree(Object)}
   *
   * @param object
   * @return
   */
  public static JsonNode valueToTree(final Object object) {
    return OBJECT_MAPPER.valueToTree(object);
  }

  public static ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }

  public static List<String> readIntoStringList(final JsonNode node) throws IOException {
    String[] arr = OBJECT_MAPPER.treeToValue(node, String[].class);
    return Arrays.asList(arr);
  }
}
