package com.avenga.task.endpoints;

import com.avenga.task.models.Author;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorEndpoints {

    public static Response getAllAuthors() {
        return given()
                .when()
                .get("/Authors");
    }

    public static Response getAuthorById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get("/Authors/{id}");
    }

    public static Response getAuthorById(String id) {
        return given()
                .pathParam("id", id)
                .when()
                .get("/Authors/{id}");
    }

    public static Response getAuthorsByBookId(int bookId) {
        return given()
                .pathParam("id", bookId)
                .when()
                .get("/Authors/authors/books/{id}");
    }

    public static Response createAuthor(Author author) {
        return given()
                .body(author)
                .when()
                .post("/Authors");
    }

    public static Response createAuthor() {
        return given()
                .when()
                .post("/Authors");
    }

    public static Response updateAuthor(int id, Author author) {
        return given()
                .pathParam("id", id)
                .body(author)
                .when()
                .put("/Authors/{id}");
    }

    public static Response updateAuthor(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .put("/Authors/{id}");
    }

    public static Response deleteAuthor(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete("/Authors/{id}");
    }

    public static Response deleteAuthor(String id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete("/Authors/{id}");
    }
}
