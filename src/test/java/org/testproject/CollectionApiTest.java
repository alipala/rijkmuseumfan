package org.testproject;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CollectionApiTest extends BaseTest{
    // Smoke tests
    @Test
    @Tag("smoke")
    public void testGetCollection(){

    }
    @Test
    @Tag("smoke")
    public void testGetCollectionWithOptionalParameter() {

    }

    @Test
    @Tag("regression")
    public void testGetCollectionWithInvalidApiKey() {

    }
    @Test
    @Tag("regression")
    public void testGetCollectionWithMissingApiKey() {

    }

    @Test
    @Tag("e2e")
    public void testGetCollectionDestructive() {

    }
    @Test
    @Tag("e2e")
    public void testSqlInjection() {

    }
    @Test
    @Tag("e2e")
    public void testGetCollectionLargePageSize() {

    }
}
