package com.avenga.task.tests.authors;

import com.avenga.task.endpoints.AuthorEndpoints;
import com.avenga.task.models.Author;
import com.avenga.task.tests.TestBase;
import com.avenga.task.util.JsonUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static com.avenga.task.assertions.ResponseAssertions.assertStatusCode;
import static com.avenga.task.assertions.ResponseAssertions.logResponse;

public class CreateAuthorTest extends TestBase {

    @Test
    public static void createAuthor(){
        Author author = JsonUtils.fromFile(DataDirectoryPath+"test-author.json", Author.class);

        Response response = AuthorEndpoints.createAuthor(author);
        assertStatusCode(response, 200);
        logResponse(response);
    }

    @Test
    public static void createAuthorWithNoBody(){
        Response response = AuthorEndpoints.createAuthor();
        assertStatusCode(response, 400);
        logResponse(response);
    }

    @Test
    public static void createAuthorWithInvalidId(){
        Author author = JsonUtils.fromFile(DataDirectoryPath+"test-author.json", Author.class);
        author.setId("abc");

        Response response = AuthorEndpoints.createAuthor(author);
        assertStatusCode(response, 400);
        logResponse(response);
    }

    @Test
    public static void createAuthorWithInvalidBookId(){
        Author author = JsonUtils.fromFile(DataDirectoryPath+"test-author.json", Author.class);
        author.setIdBook("abc");

        Response response = AuthorEndpoints.createAuthor(author);
        assertStatusCode(response, 400);
        logResponse(response);
    }

    @Test
    public static void createAuthorWithInvalidFirstName(){
        Author author = JsonUtils.fromFile(DataDirectoryPath+"test-author.json", Author.class);
        author.setFirstName("558411551");

        Response response = AuthorEndpoints.createAuthor(author);
        assertStatusCode(response, 400);
        logResponse(response);
    }
}
