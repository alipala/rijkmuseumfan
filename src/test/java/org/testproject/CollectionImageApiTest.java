package org.testproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testproject.helper.RequestHelper.assertStatusCode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testproject.base.BaseTest;
import org.testproject.helper.RequestHelper;
import org.testproject.helper.TestReporter;

import java.util.HashMap;
import java.util.Map;

public class CollectionImageApiTest extends BaseTest {
    @Test
    @Tag("smoke")
    public void testGetCollectionImage() {
        TestReporter.createTest("testGetCollectionImage");
        Response response = RequestHelper.sendGetRequest("collection/SK-C-5/tiles");
        assertStatusCode(response, HttpStatus.SC_OK);
        //assertThat(response.jsonPath().getString("levels")).isNotEmpty();
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
        TestReporter.createTest("testGetCollectionEdgeCase");
        Map<String, Object> params = new HashMap<>();
        params.put("ps", 1000); // Edge case: large page size
        Response response = RequestHelper.sendGetRequestWithOptionalParams("/collection/SK-C-5/tiles", params);
        assertStatusCode(response, HttpStatus.SC_OK);

        assertThat(response.getBody().asString()).isNotNull();
        assertThat(response.jsonPath().getString("levels.tiles")).isNotEmpty();
    }
}
