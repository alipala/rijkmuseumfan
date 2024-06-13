# Rijksmuseum API Testing Framework

## Overview
This project contains automated tests for the Rijksmuseum API using RestAssured, JUnit 5, AssertJ, and ExtentReports for HTML reporting. The tests are categorized into smoke, regression, and e2e (end-to-end) suites.

## Project Structure
- **BaseTest.java**: Contains common setup and methods.
- **HealthCheckTest.java**: Verifies the APIs are up and running.
- **CollectionApiTest.java**: Tests for the Collection API.
- **CollectionDetailsApiTest.java**: Tests for the Collection Details API.
- **CollectionImageApiTest.java**: Tests for the Collection Image API.
- **Helper Classes**: Utility classes for configuration and HTTP requests.

## Running Tests
To run the tests, use the following Maven commands:

- **Smoke tests**: `mvn test -Psmoke`
- **Regression tests**: `mvn test -Pregression`
- **E2E tests**: `mvn test -Pe2e`
- **Performance tests**: `k6 run src/main/load-test.js`

## Test Categories
- **Smoke**: Basic functionality tests.
- **Regression**: Comprehensive tests to verify existing functionality.
- **E2E**: End-to-end tests covering all aspects of the APIs.
- **Performance**: Load, stress, and spike tests covering non-functional requirements of the API.

## Test Automation Strategy
1. **Define what to test for an API**:
    - Success responses: 2xx
    - Fail responses: 4xx, 5xx
2. **Pick dependencies**:
    - JUnit 5
    - RestAssured
    - AssertJ
    - ExtentReports for HTML reporting
3. **Create Helpers**:
    - **ConfigHelper**: Read information from a properties file.
    - **RequestHelper**: Manage HTTP requests and responses.
    - **TestReporter**: Generate HTML reports once tests are completed.
4. **Select Running Suites**:
    - **Collection API Test**:
        - Smoke
        - Regression
        - E2E
    - **Collection Details API Test**:
        - Smoke
        - Regression
        - E2E
    - **Collection Image API Test**:
        - Smoke
        - Regression
        - E2E

## Dependencies
The project uses the following dependencies:

- JUnit 5
- RestAssured
- AssertJ
- ExtentReports

## API Details
- **Collection API**: `/collection`
- **Collection Details API**: `/collection/{objectNumber}`
- **Collection Image API**: `/collection/{objectNumber}/image`

## Setup
1. Clone the repository.
2. Update the `baseURI` in `BaseTest.java`.
3. Add your API key in the properties file.
4. Run the tests using the Maven commands.

## Continuous Integration
The project is set up to run tests in an Azure DevOps pipeline, executing smoke tests first, followed by regression tests, and finally, e2e tests. Performance tests are run after functional tests.
