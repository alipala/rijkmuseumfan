package org.testproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionImageApiTest extends BaseTest{
    final static String API_KEY = "";

    @Test
    @Tag("smoke")
    public void testGetCollectionImage() {
        Response response = RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .when()
                .get("collection/SK-C-5/tiles")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        assertThat(response.jsonPath().getString("levels")).isNotEmpty();
    }

    @Test
    @Tag("regression")
    public void testGetCollectionWithInvalidId() {
        // Implement test for invalid id and expect status 404 not found

    }

    @Test
    @Tag("regression")
    public void testGetCollectionWithInvalidApiKey() {
        RestAssured
                .given()
                .queryParam("key", "API_KEY")
                .queryParam("format", "json")
                .when()
                .get("/collection/SK-C-5/image")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED); // Assuming 401 Unauthorized for invalid API key
    }

    @Test
    @Tag("e2e")
    public void testGetCollectionDestructive() {

    }

    @Test
    @Tag("e2e")
    public void testGetCollectionEdgeCase() {
        Response response = RestAssured
                .given()
                .log().all()
                .queryParam("key", API_KEY)
                .queryParam("format", "json")
                .queryParam("ps", 1000) // Edge case: large page size
                .when()
                .get("/collection/SK-C-5/tiles")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        assertThat(response.getBody().asString()).isNotNull();
        assertThat(response.jsonPath().getString("levels.tiles")).isNotEmpty();

    }
}
