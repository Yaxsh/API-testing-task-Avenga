package com.avenga.task.endpoints;

import io.restassured.response.Response;
import com.avenga.task.models.Book;

import static io.restassured.RestAssured.given;

public class BookEndpoints {

    public static Response getAllBooks() {
        return given()
                .when()
                .get("/Books");
    }

    public static Response getBookById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get("/Books/{id}");
    }

    public static Response createBook(Book book) {
        return given()
                .body(book)
                .when()
                .post("/Books");
    }

    public static Response updateBook(int id, Book book) {
        return given()
                .pathParam("id", id)
                .body(book)
                .when()
                .put("/Books/{id}");
    }

    public static Response deleteBook(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete("/Books/{id}");
    }
}

