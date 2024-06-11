package org.testproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CollectionApiTest extends BaseTest{
    final static String API_KEY = "";
    // Smoke tests
    @Test
    @Tag("smoke")
    public void testGetCollection(){
        Response response = RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .queryParam("ps", 30)
                .when()
                .get("/collection")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.getBody().asString()).as("Checking if response is null").isNotNull();
        assertThat(response.getStatusCode()).as("Checking if response code is correct").isEqualTo(200);
    }
    @Test
    @Tag("smoke")
    public void testGetCollectionWithOptionalParameter() {
        Response response = RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .queryParam("imgonly", true)
                .when()
                .get("/collection")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.getBody().asString()).as("Checking if response is null").isNotNull();
        boolean hasArtObjects = response.jsonPath().getList("artObjects").size() > 0;
        assertThat(hasArtObjects).isTrue();
    }

    @Test
    @Tag("regression")
    public void testGetCollectionWithInvalidApiKey() {
        // Implement test for invalid api key and expect status 403 forbidden
    }
    @Test
    @Tag("regression")
    public void testGetCollectionWithMissingApiKey() {
        // Implement test for missing api key and expect status 401 unauthorized
    }

    @Test
    @Tag("e2e")
    public void testGetCollectionDestructive() {
        RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .queryParam("p", -1)
                .queryParam("culture", "tr") // Destructive scenario
                .when()
                .get("/collection")
                .then()
                .log().all()
                .statusCode(400); // Assuming 400 bad request

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
                .get("/collection")
                .then()
                .statusCode(400);

    }
    @Test
    @Tag("e2e")
    public void testGetCollectionLargePageSize() {
        Response response = RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .queryParam("ps", "100") // Edge case: large page size
                .when()
                .get("/collection")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        assertThat(response.getBody()).isNotNull();
    }
}
