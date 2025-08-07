package com.avenga.task.tests;

import com.avenga.task.base.TestBase;
import com.avenga.task.endpoints.BookEndpoints;
import io.restassured.response.Response;
import com.avenga.task.models.Book;
import org.testng.annotations.Test;
import com.avenga.task.util.JsonUtils;
import static com.avenga.task.assertions.ResponseAssertions.*;

public class CreateBookTest extends TestBase {

    @Test
    public void createBook() {
        Book newBook = JsonUtils.fromFile(DataDirectoryPath+"test-book.json", Book.class);

        Response response = BookEndpoints.createBook(newBook);

        assertStatusCode(response, 200);
//        assertJsonField(response, "id", newBook.getId());
//        assertJsonField(response, "title", newBook.getTitle());
        logResponse(response);
    }

    @Test
    public void createBook_withDuplicateId_shouldReturnError() {
        Book duplicateBook = JsonUtils.fromFile(DataDirectoryPath+"book29.json", Book.class);

        Response response = BookEndpoints.createBook(duplicateBook);

        assertStatusCode(response, 500); // Expected for duplicate ID in FakeRestAPI
        logResponse(response);
    }
}

