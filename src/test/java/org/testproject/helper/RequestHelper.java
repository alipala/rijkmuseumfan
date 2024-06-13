package org.testproject.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestHelper {
    //private static final String API_KEY = System.getenv("TEST_API_KEY");
    private static final String API_KEY = ConfigHelper.getProperty("api.key");

    public static Response sendGetRequest(String endpoint) {
        try {
            Response response = RestAssured
                    .given()
                    .log().all()
                    .queryParam("key", API_KEY)
                    .when()
                    .get(endpoint)
                    .then()
                    .log().all()
                    .extract()
                    .response();
            TestReporter.getTest().pass("GET request to " + endpoint + " was successful");
            return response;
        } catch (Exception e) {
            TestReporter.getTest().fail("GET request to " + endpoint + " failed with exception: " + e.getMessage());
            throw e;
        }
    }

    public static Response sendGetRequestWithOptionalParams(String endpoint, Map<String, Object> params) {
        try {
            Response response = RestAssured
                    .given()
                    .log().all()
                    .queryParam("key", API_KEY)
                    .queryParams(params)
                    .when()
                    .get(endpoint)
                    .then()
                    .log().all()
                    .extract()
                    .response();
            TestReporter.getTest().pass("GET request to " + endpoint + " was successful");
            return response;
        } catch (Exception e) {
            TestReporter.getTest().fail("GET request to " + endpoint + " with params " + params + " failed with exception: " + e.getMessage());
            throw e;
        }
    }

    public static void assertStatusCode(Response response, int statusCode) {
        try {
            assertThat(response.getStatusCode())
                    .as("Checking if status code is correct")
                    .isEqualTo(statusCode);
            TestReporter.getTest().pass("Status code is correct: " + statusCode);
        } catch (AssertionError e) {
            TestReporter.getTest().fail("Status code assertion failed: expected " + statusCode + " but got " + response.getStatusCode());
            throw e;
        }
    }

    public static void assertResponseNotNull(Response response) {
        try {
            assertThat(response.getBody().asString()).isNotNull();
            TestReporter.getTest().pass("Response is not null");
        } catch (AssertionError e) {
            TestReporter.getTest().fail("Response is null");
            throw e;
        }
    }
}
