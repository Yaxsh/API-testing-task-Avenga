package com.avenga.task.tests.books;

import com.avenga.task.tests.TestBase;
import com.avenga.task.endpoints.BookEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static com.avenga.task.assertions.ResponseAssertions.*;

public class DeleteBookTest extends TestBase {

    @Test
    public void deleteBook() {
        int bookIdToDelete = 15;

        Response response = BookEndpoints.deleteBook(bookIdToDelete);

        assertStatusCode(response, 200);
        logResponse(response);
    }

    @Test
    public void deleteBookWithInvalidId() {
        int invalidId = 88888;

        Response response = BookEndpoints.deleteBook(invalidId);

        assertStatusCode(response, 404);
        logResponse(response);
    }

    @Test
    public void deleteBookWithNonIntId() {
        String invalidId = "abc";

        Response response = BookEndpoints.deleteBook(invalidId);

        assertStatusCode(response, 400);
        logResponse(response);
    }

    @Test
    public void deleteBookWithNoId() {
        String NoId = "";

        Response response = BookEndpoints.deleteBook(NoId);

        assertStatusCode(response, 405);
        logResponse(response);
    }
}
