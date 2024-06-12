# Rijksmuseum API Testing

## Overview
This project contains automated tests for the Rijksmuseum API using RestAssured, JUnit 5, and AssertJ. The tests are categorized into smoke, regression, and e2e suites.

## Project Structure
- **BaseTest.java**: Contains common setup and methods.
- **HealthCheckTest.java**: Verifies the APIs are up and running.
- **CollectionApiTest.java**: Tests for the Collection API.
- **CollectionDetailsApiTest.java**: Tests for the Collection Details API.
- **CollectionImageApiTest.java**: Tests for the Collection Image API.

## Running Tests
To run the tests, use the following Maven commands:

- **Smoke tests**: `mvn test -Psmoke`
- **Regression tests**: `mvn test -Pregression`
- **E2E tests**: `mvn test -Pe2e`
- **Performance tests**: ` k6 run src/main/load-test.js`

## Test Categories
- **Smoke**: Basic functionality tests.
- **Regression**: Comprehensive tests to verify existing functionality.
- **E2E**: End-to-end tests covering all aspects of the APIs.
- **Performance**: Load, stress and spike tests covering non-functional requirements of API

## Dependencies
The project uses the following dependencies:
- JUnit 5
- RestAssured
- AssertJ

## API Details
- **Collection API**: `/collection`
- **Collection Details API**: `/collection/{objectNumber}`
- **Collection Image API**: `/collection/{objectNumber}/image`

## Setup
1. Clone the repository.
2. Update the `baseURI` in `BaseTest.java`.
3. Add your API key in the test classes.
4. Run the tests using the Maven commands.

## Contributing
Feel free to open issues or submit pull requests with improvements or additional tests.
