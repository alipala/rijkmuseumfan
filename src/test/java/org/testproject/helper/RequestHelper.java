package org.testproject.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestHelper {
    //private static final String API_KEY = System.getenv("TEST_API_KEY");
    private static final String API_KEY = ConfigHelper.getProperty("api.key");

    public static Response sendGetRequest(String endpoint) {
        return RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response sendGetRequestWithOptionalParams(String endpoint, Map<String, Object> params) {
        return RestAssured
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
    }

    public static void assertStatusCode(Response response, int statusCode) {
        assertThat(response.getStatusCode())
                .as("Checking if status code is correct")
                .isEqualTo(statusCode);
    }

    public static void assertResponseNotNull(Response response) {
        assertThat(response.getBody().asString()).isNotNull();
    }
}
