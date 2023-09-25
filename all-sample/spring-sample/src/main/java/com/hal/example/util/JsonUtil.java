package com.hal.example.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

public class JsonUtil {

  /**
   * The Class SingletonHolder.
   */
  private static final class SingletonHolder {

    /**
     * The instance.
     */
    private static ObjectMapper instance;
  }

  /**
   * get objectMapper instance.
   *
   * @param createNew the create new
   * @return the mapper instance
   */
  private static synchronized ObjectMapper getMapperInstance(boolean createNew) {
    if (createNew || SingletonHolder.instance == null) {
      SingletonHolder.instance = new ObjectMapper();
    }
    return SingletonHolder.instance;
  }

  /**
   * Json to.txt object.
   *
   * @param <T> the generic type
   * @param json the json
   * @param clazz the clazz
   * @return the t
   */
  public static <T> T jsonToObject(String json, Class<T> clazz) throws Exception {
    try {
      return getMapperInstance(false).readValue(json, clazz);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  public static <T> T jsonToObject(File file, Class<T> clazz) throws Exception {
    try {
      return getMapperInstance(false).readValue(file, clazz);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }


  /**
   * Json to.txt object.
   *
   * @param <T> the generic type
   * @param json the jsonNode
   * @param clazz the clazz
   * @return the t
   * @throws Exception the techincal exception
   */
  public static <T> T jsonToObject(JsonNode json, Class<T> clazz) throws Exception {
    try {
      return getMapperInstance(false).treeToValue(json, clazz);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  /**
   * Convert Json to.txt the list of Object
   *
   * @param <T> the generic type
   * @param json the json
   * @param clazz the clazz
   * @return The list of T
   */
  public static <T> List<T> jsonToObjList(JsonNode json, Class<T> clazz) throws Exception {
    try {
      if (json.isArray()) {
        return getMapperInstance(false).readValue(json.toString(), getListType(clazz));
      } else {
        return emptyList();
      }
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  /**
   * Object to.txt json.
   *
   * @param <T> the generic type
   * @param object the object
   * @return the string
   */
  public static <T> String objectToJson(Object object) throws Exception {
    try {
      return getMapperInstance(false).setSerializationInclusion(Include.NON_NULL)
          .writeValueAsString(object);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  /**
   * String to.txt JsonNode
   *
   * @param jsonStr the String
   * @return JsonNode
   */
  public static JsonNode stringToJsonNode(String jsonStr) throws Exception {
    try {
      return getMapperInstance(false).setSerializationInclusion(Include.NON_NULL)
          .readTree(jsonStr);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  public static JsonNode fileToJsonNode(File file) throws Exception {
    try {
      return getMapperInstance(false).setSerializationInclusion(Include.NON_NULL)
          .readTree(file);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }


  /**
   * Object to.txt json with subTypes registration.
   *
   * @param <T> the generic type
   * @param object the object
   * @param subTypeClasses the sub type classes
   * @return the string
   * @throws Exception the exception
   */
  @SafeVarargs
  public static <T> String objectToJsonExtended(Object object, Class<T>... subTypeClasses)
      throws Exception {
    try {
      ObjectMapper mapper = getMapperInstance(!ArrayUtils.isEmpty(subTypeClasses));
      mapper.registerSubtypes(subTypeClasses);
      return mapper.writeValueAsString(object);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  /**
   * Creates the json object.
   *
   * @return the object node
   */
  public static ObjectNode newJSONObject() {
    return getMapperInstance(false).createObjectNode();
  }

  /**
   * New json array.
   *
   * @return the array node
   */
  public static ArrayNode newJSONArray() {
    return getMapperInstance(false).createArrayNode();
  }

  private static CollectionType getListType(Class<?> target) {
    return getMapperInstance(false).getTypeFactory().constructCollectionType(List.class, target);
  }
}
