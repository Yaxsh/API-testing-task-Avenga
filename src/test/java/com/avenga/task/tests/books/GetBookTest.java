package com.avenga.task.tests.books;

import com.avenga.task.assertions.BookAssertions;
import com.avenga.task.base.TestBase;
import com.avenga.task.endpoints.BookEndpoints;
import com.avenga.task.models.Book;
import com.avenga.task.util.JsonUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.avenga.task.assertions.ResponseAssertions.*;

public class GetBookTest extends TestBase {

    @Test
    public void getAllBooks_shouldReturn200AndListOfBooks() {
        Response response = BookEndpoints.getAllBooks();

        assertStatusCode(response, 200);
        logResponse(response);
    }

    @Test
    public void getBookById_shouldReturnCorrectBook() {
        int bookId = 1;

        Response response = BookEndpoints.getBookById(bookId);

        assertStatusCode(response, 200);
        Book book = JsonUtils.fromResponse(response, Book.class);
        Assert.assertEquals(book.getId(), bookId);
        logResponse(response);
    }

    @Test
    public void getBookByInvalidId_shouldReturn404() {
        int invalidId = 99999;

        Response response = BookEndpoints.getBookById(invalidId);

        assertStatusCode(response, 404);
        logResponse(response);
    }

    @Test
    public void getBookByIdAndVerify() {
        int bookId = 29;

        Response response = BookEndpoints.getBookById(bookId);

        assertStatusCode(response, 200);
        Book bookExpected = JsonUtils.fromFile(DataDirectoryPath+"book29.json", Book.class);
        Book bookActual = JsonUtils.fromResponse(response, Book.class);
        Assert.assertEquals(bookExpected.getId(), bookId);
        BookAssertions.assertTwoBooksEqual(bookExpected, bookActual);
        logResponse(response);
    }
}
