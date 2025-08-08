package com.avenga.task.tests.authors;

import com.avenga.task.endpoints.AuthorEndpoints;
import com.avenga.task.models.Author;
import com.avenga.task.tests.TestBase;
import com.avenga.task.util.JsonUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static com.avenga.task.assertions.ResponseAssertions.assertStatusCode;
import static com.avenga.task.assertions.ResponseAssertions.logResponse;

public class GetAuthorTest extends TestBase {

    @Test
    public static void getAllAuthors(){
        Response response = AuthorEndpoints.getAllAuthors();

        assertStatusCode(response, 200);
        List<Author> allAuthors = JsonUtils.fromResponseToAuthorList(response);
        Assert.assertTrue(allAuthors.size()>100, "Response authors list is not as expected.");
        logResponse(response);
    }

    @Test
    public static void getAuthorById(){
        Response response = AuthorEndpoints.getAuthorById(15);

        assertStatusCode(response, 200);
        Author authorById = JsonUtils.fromResponse(response, Author.class);
        Assert.assertEquals(authorById.getId(), "15", "Response author id is not as expected.");
        logResponse(response);
    }

    @Test
    public static void getAuthorsByBookId(){
        Response response = AuthorEndpoints.getAuthorsByBookId(15);

        assertStatusCode(response, 200);
        List<Author> allBookAuthors = JsonUtils.fromResponseToAuthorList(response);
        Assert.assertTrue(allBookAuthors.size()>3, "Response authors list is not as expected.");
        logResponse(response);
    }

    @Test
    public static void getAuthorByNonExistentId(){
        Response response = AuthorEndpoints.getAuthorById(9999);

        assertStatusCode(response, 404);
        logResponse(response);
    }

    @Test
    public static void getAuthorByInvalidId(){
        Response response = AuthorEndpoints.getAuthorById("abc");

        assertStatusCode(response, 400);
        logResponse(response);
    }
}
