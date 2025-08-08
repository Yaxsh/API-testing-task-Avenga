package com.avenga.task.tests.authors;

import com.avenga.task.assertions.AuthorAssertions;
import com.avenga.task.assertions.ResponseAssertions;
import com.avenga.task.endpoints.AuthorEndpoints;
import com.avenga.task.models.Author;
import com.avenga.task.tests.TestBase;
import com.avenga.task.util.JsonUtils;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UpdateAuthorTest extends TestBase {

    @Test
    public static void updateAuthor(){
        Allure.step("Get existing author");
        Author author = JsonUtils.fromResponse(AuthorEndpoints.getAuthorById(1), Author.class);

        Allure.step("Update existing author");
        author.setFirstName("Nikola");
        author.setLastName("Babamov");
        Response response = AuthorEndpoints.updateAuthor(1, author);
        ResponseAssertions.assertStatusCode(response, 200);

        Allure.step("Verify response");
        Author updatedAuthor = JsonUtils.fromResponse(response, Author.class);
        AuthorAssertions.assertTwoAuthorsEqual(author, updatedAuthor);
    }

    @Test
    public static void updateAuthorWithEmptyBody(){
        Response response = AuthorEndpoints.updateAuthor(1);
        ResponseAssertions.assertStatusCode(response, 400);
    }

    @Test
    public static void updateAuthorWithInvalidId(){
        Author author = JsonUtils.fromFile(DataDirectoryPath+"test-author.json", Author.class);
        author.setId("TestId");

        Response response = AuthorEndpoints.updateAuthor(1, author);
        ResponseAssertions.assertStatusCode(response, 400);
    }

    @Test
    public static void updateAuthorWithInvaliBookdId(){
        Author author = JsonUtils.fromFile(DataDirectoryPath+"test-author.json", Author.class);
        author.setIdBook("TestBookId");

        Response response = AuthorEndpoints.updateAuthor(1, author);
        ResponseAssertions.assertStatusCode(response, 400);
    }
}
