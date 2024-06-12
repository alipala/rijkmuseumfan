package org.testproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testproject.base.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionDetailsApiTest extends BaseTest {
//    final static String API_KEY = System.getenv("TEST_API_KEY");
    final static String API_KEY = "";

    @Test
    @Tag("smoke")
    public void testGetCollectionDetails() {
        Response response = RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                //.pathParam("object-number", "SK-C-5")
                .when()
                .get("/collection/SK-C-5")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.getBody().asString()).isNotNull();
        assertThat(response.jsonPath().getString("artObject.title")).isEqualTo("The Night Watch");

    }

    @Test
    @Tag("regression")
    public void testGetCollectionDetailsWithInvalidId() {
        RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .when()
                .get("/collection/INVALID-ID")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .extract().response();
    }

    @Test
    @Tag("regression")
    public void testGetCollectionDetailsWithMissingApiKey() {
        RestAssured.given()
                .log().all()
                .when()
                .get("/collection/SK-C-5")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract().response();
    }

    @Test
    @Tag("e2e")
    public void testGetCollectionDetailsDestructive() {
        RestAssured.given()
                .log().all()
                .queryParam("key", API_KEY)
                .queryParam("imgonly", true)
                .when()
                .get("/collection/SK-C-5")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag("e2e")
    public void testSqlInjection() {
        RestAssured
                .given()
                .queryParam("key", API_KEY)
                .queryParam("format", "json")
                .queryParam("q", "1 OR 1=1") // Destructive: SQL Injection attempt
                .when()
                .get("/collection/SK-C-5")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
