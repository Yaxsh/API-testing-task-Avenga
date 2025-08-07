package com.avenga.task.assertions;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ResponseAssertions {

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public static void logResponse(Response response) {
        response.then().log().all();
    }
}

