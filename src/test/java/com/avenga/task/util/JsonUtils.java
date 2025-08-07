package com.avenga.task.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Convert Java object to JSON string.
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Convert JSON string to Java object.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize JSON to object: " + e.getMessage(), e);
        }
    }

    /**
     * Deserialize a RestAssured Response body into a Java object.
     */
    public static <T> T fromResponse(Response response, Class<T> clazz) {
        return fromJson(response.getBody().asString(), clazz);
    }

    public static <T> T fromFile(String filePath, Class<T> clazz) {
        try {
            String json = readFileAsString(filePath);
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize JSON to object: " + e.getMessage(), e);
        }
    }

    private static String readFileAsString(String filePath) throws IOException {
        // Read all bytes from the file and then convert them to a String using the default charset
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}

