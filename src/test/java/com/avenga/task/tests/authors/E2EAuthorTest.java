package com.avenga.task.tests.authors;

import com.avenga.task.assertions.AuthorAssertions;
import com.avenga.task.assertions.ResponseAssertions;
import com.avenga.task.endpoints.AuthorEndpoints;
import com.avenga.task.models.Author;
import com.avenga.task.tests.TestBase;
import com.avenga.task.util.JsonUtils;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.avenga.task.assertions.ResponseAssertions.assertStatusCode;
import static com.avenga.task.assertions.ResponseAssertions.logResponse;

public class E2EAuthorTest extends TestBase {

    @Test
    public static void E2EAuthorTestFlow(){
        Allure.step("Get author");
        Response getAuthorResponse = AuthorEndpoints.getAuthorById(15);
        assertStatusCode(getAuthorResponse, 200);
        Author authorById = JsonUtils.fromResponse(getAuthorResponse, Author.class);
        Assert.assertEquals(authorById.getId(), "15", "Response author id is not as expected.");
        logResponse(getAuthorResponse);

        Allure.step("Update author");
        authorById.setFirstName("Nikola");
        authorById.setLastName("Babamov");
        Response updateAuthorResponse = AuthorEndpoints.updateAuthor(1, authorById);
        ResponseAssertions.assertStatusCode(updateAuthorResponse, 200);
        Author updatedAuthor = JsonUtils.fromResponse(updateAuthorResponse, Author.class);
        AuthorAssertions.assertTwoAuthorsEqual(authorById, updatedAuthor);

        Allure.step("Delete author");
        Response deleteAuthorResponse = AuthorEndpoints.deleteAuthor(authorById.getId());
        ResponseAssertions.assertStatusCode(deleteAuthorResponse, 200);
    }
}
