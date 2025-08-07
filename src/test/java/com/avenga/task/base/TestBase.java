package com.avenga.task.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public abstract class TestBase {

    protected static RequestSpecification requestSpec;

    protected static final String DataDirectoryPath = "src/test/java/com/avenga/task/data/";

    @BeforeClass
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://fakerestapi.azurewebsites.net")
                .setBasePath("/api/v1")
                .setContentType("application/json")
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
}
