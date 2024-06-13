package org.testproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testproject.helper.RequestHelper.assertResponseNotNull;
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

public class CollectionDetailsApiTest extends BaseTest {
    @Test
    @Tag("smoke")
    public void testGetCollectionDetails() {
        TestReporter.createTest("testGetCollectionDetails");
        Response response = RequestHelper.sendGetRequest("/collection/SK-C-5");
        assertStatusCode(response, HttpStatus.SC_OK);
        //assertThat(response.jsonPath().getString("artObject.title")).isEqualTo("The Night Watch");
    }

    @Test
    @Tag("regression")
    public void testGetCollectionDetailsWithInvalidId() {
        TestReporter.createTest("testGetCollectionDestructive");
        Response response = RequestHelper.sendGetRequest("/collection/invalid-id");
        assertStatusCode(response, HttpStatus.SC_INTERNAL_SERVER_ERROR);
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
        TestReporter.createTest("testGetCollectionDetailsDestructive");
        Map<String, Object> params = new HashMap<>();
        params.put("imgonly", true);
        Response response = RequestHelper.sendGetRequestWithOptionalParams("/collection/SK-C-5", params);
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag("e2e")
    public void testSqlInjection() {
        TestReporter.createTest("testSqlInjection");
        Map<String, Object> params = new HashMap<>();
        params.put("q", "1 OR 1=1");
        Response response = RequestHelper.sendGetRequestWithOptionalParams("/collection/SK-C-5", params);
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }
}
