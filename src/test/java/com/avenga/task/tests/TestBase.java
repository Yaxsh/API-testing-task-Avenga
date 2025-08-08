package com.avenga.task.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public abstract class TestBase {

    protected static RequestSpecification requestSpec;
    protected static SoftAssert softAssert;

    protected static final String DataDirectoryPath = "src/test/java/com/avenga/task/data/";

    @BeforeClass
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://fakerestapi.azurewebsites.net")
                .setBasePath("/api/v1")
                .setContentType("application/json")
                .build();

        RestAssured.requestSpecification = requestSpec;

        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown(){
        softAssert.assertAll();
    }
}
