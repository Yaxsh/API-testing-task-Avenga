package com.avenga.task.tests.books;

import com.avenga.task.assertions.BookAssertions;
import com.avenga.task.tests.TestBase;
import com.avenga.task.endpoints.BookEndpoints;
import com.avenga.task.util.JsonUtils;
import io.restassured.response.Response;
import com.avenga.task.models.Book;
import org.testng.annotations.Test;
import static com.avenga.task.assertions.ResponseAssertions.*;

public class UpdateBookTest extends TestBase {

    @Test
    public void updateBook_shouldReturn200AndUpdatedFields() {
        int bookId = 15;
        Book updatedBook = new Book(
                bookId,
                "Updated Title",
                "Updated description",
                321,
                "Updated excerpt",
                "2025-09-01T00:00:00"
        );

        Response response = BookEndpoints.updateBook(bookId, updatedBook);
        assertStatusCode(response, 200);

        Book responseBook = JsonUtils.fromResponse(response, Book.class);
        BookAssertions.assertTwoBooksEqual(responseBook, updatedBook);
        logResponse(response);
    }

    @Test
    public void updateNonExistingBook() {
        int nonExistingId = 99999;
        Book dummyBook = new Book(nonExistingId, "Ghost", "", 100, "", "2025-01-01T00:00:00");

        Response response = BookEndpoints.updateBook(nonExistingId, dummyBook);

        assertStatusCode(response, 404);
        logResponse(response);
    }

    @Test
    public void updateWithNoBody() {
        int nonExistingId = 15;

        Response response = BookEndpoints.updateBook(nonExistingId);

        assertStatusCode(response, 400);
        logResponse(response);
    }
}

