package org.testproject.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testproject.helper.TestReporter;

public abstract class BaseTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://www.rijksmuseum.nl/api/en";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        TestReporter.setUpReport();
    }

    @AfterAll
    public static void tearDown() {
        TestReporter.tearDownReport();
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
