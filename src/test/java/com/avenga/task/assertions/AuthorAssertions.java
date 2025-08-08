package com.avenga.task.assertions;

import com.avenga.task.models.Author;
import org.testng.Assert;

public class AuthorAssertions {

    public static void assertTwoAuthorsEqual(Author authorExpected, Author authorActual){
        Assert.assertTrue(authorExpected.equals(authorActual), "Authors are not identical");
    }
}
