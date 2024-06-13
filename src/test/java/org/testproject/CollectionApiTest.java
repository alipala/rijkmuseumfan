package org.testproject;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testproject.base.BaseTest;
import org.testproject.helper.RequestHelper;
import org.testproject.helper.TestReporter;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testproject.helper.RequestHelper.*;

public class CollectionApiTest extends BaseTest {

    public static final String ENDPOINT = "/collection";

    // Smoke test
    @Test
    @Tag("smoke")
    public void testGetCollection() {
        TestReporter.createTest("testGetCollection");
        Response response = RequestHelper.sendGetRequest(ENDPOINT);
        assertStatusCode(response, HttpStatus.SC_OK);
        assertResponseNotNull(response);
    }

    @Test
    @Tag("smoke")
    public void testGetCollectionWithOptionalParameter() {
        TestReporter.createTest("testGetCollectionWithOptionalParameter");
        Map<String, Object> params = new HashMap<>();
        params.put("imgonly", true);

        Response response = sendGetRequestWithOptionalParams(ENDPOINT, params);
        assertStatusCode(response, HttpStatus.SC_OK);
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
        TestReporter.createTest("testGetCollectionDestructive");
        Map<String, Object> params = new HashMap<>();
        params.put("p", -1);
        params.put("culture", "tr");

        Response response = sendGetRequestWithOptionalParams(ENDPOINT, params);
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag("e2e")
    public void testSqlInjection() {
        TestReporter.createTest("testSqlInjection");
        Map<String, Object> params = new HashMap<>();
        params.put("format", "json");
        params.put("q", "1 OR 1=1"); // Destructive: SQL Injection attempt

        Response response = sendGetRequestWithOptionalParams(ENDPOINT, params);
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag("e2e")
    public void testGetCollectionLargePageSize() {
        TestReporter.createTest("testGetCollectionLargePageSize");
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1000);

        Response response = sendGetRequestWithOptionalParams(ENDPOINT, params);
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        assertResponseNotNull(response);
    }
}
