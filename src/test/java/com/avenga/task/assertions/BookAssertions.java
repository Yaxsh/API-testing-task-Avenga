package com.avenga.task.assertions;

import com.avenga.task.models.Book;
import org.testng.Assert;

public class BookAssertions {

    public static void assertTwoBooksEqual(Book bookExpected, Book bookActual){
        Assert.assertTrue(bookExpected.equals(bookActual), "Books are not identical");
    }
}
