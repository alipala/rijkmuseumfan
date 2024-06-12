package org.testproject.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://www.rijksmuseum.nl/api/en";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected void healthCheck() {
        RestAssured.
                given().
                when().
                get("/health").
                then().
                statusCode(200);

    }
}
