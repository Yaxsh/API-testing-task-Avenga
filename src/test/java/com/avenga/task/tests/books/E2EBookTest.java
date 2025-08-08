package com.avenga.task.tests.books;

import com.avenga.task.assertions.BookAssertions;
import com.avenga.task.tests.TestBase;
import com.avenga.task.endpoints.BookEndpoints;
import com.avenga.task.models.Book;
import com.avenga.task.util.JsonUtils;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static com.avenga.task.assertions.ResponseAssertions.assertStatusCode;
import static com.avenga.task.assertions.ResponseAssertions.logResponse;

public class E2EBookTest extends TestBase {

    @Test
    public void FullBookFlow() {
        Allure.step("Get all books");
        List<Book> allBooks = JsonUtils.fromResponseToBookList(BookEndpoints.getAllBooks());
        BookAssertions.assertBookListLength(allBooks, 200);

        Allure.step("Update book with ID 29");
        Book updatedBook = JsonUtils.fromFile(DataDirectoryPath+"book29.json", Book.class);
        Response updateResponse = BookEndpoints.updateBook(29, updatedBook);
        Book responseUpdateBook = JsonUtils.fromResponse(updateResponse, Book.class);
        BookAssertions.assertTwoBooksEqual(updatedBook, responseUpdateBook);
        logResponse(updateResponse);

        Allure.step("Get updated book with ID 29 and verify");
        Book updatedBookById = JsonUtils.fromResponse(BookEndpoints.getBookById(29), Book.class);
        softAssert.assertEquals(updatedBook.getDescription(), updatedBookById.getDescription(), "Description not matching for updated book.");
        System.out.println(updatedBook.getDescription());
        System.out.println(updatedBookById.getDescription());

        Allure.step("Delete book with ID 29");
        Response deleteResponse = BookEndpoints.deleteBook(29);
        assertStatusCode(deleteResponse, 200);
        logResponse(deleteResponse);
    }
}
