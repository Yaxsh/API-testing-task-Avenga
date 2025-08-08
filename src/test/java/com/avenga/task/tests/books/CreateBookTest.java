package com.avenga.task.tests.books;

import com.avenga.task.tests.TestBase;
import com.avenga.task.endpoints.BookEndpoints;
import io.restassured.response.Response;
import com.avenga.task.models.Book;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.avenga.task.util.JsonUtils;
import static com.avenga.task.assertions.ResponseAssertions.*;

public class CreateBookTest extends TestBase {

    @Test
    public void createBook() {
        Book newBook = JsonUtils.fromFile(DataDirectoryPath+"test-book.json", Book.class);

        Response response = BookEndpoints.createBook(newBook);

        assertStatusCode(response, 200);
        logResponse(response);
    }

    @Test
    public void createBook_withDuplicateId_shouldReturnError() {
        Book duplicateBook = JsonUtils.fromFile(DataDirectoryPath+"book29.json", Book.class);

        Response response = BookEndpoints.createBook(duplicateBook);

        assertStatusCode(response, 500);
        logResponse(response);
    }

    @Test
    public void createBookWithNoBody() {
        Response response = BookEndpoints.createBook();

        assertStatusCode(response, 400);
        logResponse(response);
    }

    @Test
    public void createBookWithMisingId() {
        Book book = new Book();
        book.setDescription("Desc");
        book.setExcerpt("Excerpt");
        book.setTitle("Book title");
        book.setPublishDate("2020-02-02T14:19:41.505Z");
        book.setPageCount(15);

        Response response = BookEndpoints.createBook(book);

        assertStatusCode(response, 200);
        Book responseBook = JsonUtils.fromResponse(response, Book.class);
        Assert.assertEquals(responseBook.getId(), 0, "Default ID not set by API.");
        logResponse(response);
    }

    @Test
    public void createBookWithFaultyTimestamp() {
        Book book = new Book();
        book.setDescription("Desc");
        book.setExcerpt("Excerpt");
        book.setTitle("Book title");
        book.setPublishDate("wrong");
        book.setPageCount(15);
        book.setId(10);

        Response response = BookEndpoints.createBook(book);

        assertStatusCode(response, 400);
        logResponse(response);
    }
}

