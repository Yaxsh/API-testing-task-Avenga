package com.avenga.task.tests.authors;

import com.avenga.task.assertions.ResponseAssertions;
import com.avenga.task.endpoints.AuthorEndpoints;
import com.avenga.task.tests.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteAuthorTest extends TestBase {

    @Test
    public static void deleteAuthorById(){
        Response response = AuthorEndpoints.deleteAuthor(1);
        ResponseAssertions.assertStatusCode(response, 200);
    }

    @Test
    public static void deleteAuthorByNonExistentId(){
        Response response = AuthorEndpoints.deleteAuthor(9999);
        ResponseAssertions.assertStatusCode(response, 404);
    }

    @Test
    public static void deleteAuthorByInvalidId(){
        Response response = AuthorEndpoints.deleteAuthor("Abc");
        ResponseAssertions.assertStatusCode(response, 400);
        ResponseAssertions.logResponse(response);
    }

    @Test
    public static void deleteAuthorByNoId(){
        Response response = AuthorEndpoints.deleteAuthor("");
        ResponseAssertions.assertStatusCode(response, 405);
        ResponseAssertions.logResponse(response);
    }
}
