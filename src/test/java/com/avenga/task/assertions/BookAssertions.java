package com.avenga.task.assertions;

import com.avenga.task.models.Book;
import org.testng.Assert;

import java.util.List;

public class BookAssertions {

    public static void assertTwoBooksEqual(Book bookExpected, Book bookActual){
        Assert.assertTrue(bookExpected.equals(bookActual), "Books are not identical");
    }

    public static void assertBookListLength(List<Book> bookList, int size){
        Assert.assertEquals(bookList.size(), size, "Book list not of expected length.");
    }
}
