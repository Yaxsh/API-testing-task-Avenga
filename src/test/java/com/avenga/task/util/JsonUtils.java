package com.avenga.task.util;

import com.avenga.task.models.Author;
import com.avenga.task.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON: " + e.getMessage(), e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize JSON to object: " + e.getMessage(), e);
        }
    }

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
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static <T> List<T> fromResponseToList(Response response, TypeReference<List<T>> typeRef) {
        try {
            String json = response.getBody().asString();
            return mapper.readValue(json, typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize response to list: " + e.getMessage(), e);
        }
    }

    public static List<Book> fromResponseToBookList(Response response) {
        return fromResponseToList(response, new TypeReference<List<Book>>() {});
    }

    public static List<Author> fromResponseToAuthorList(Response response) {
        return fromResponseToList(response, new TypeReference<List<Author>>() {});
    }
}

