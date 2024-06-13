#!/bin/bash

# Run smoke tests
echo "Running Smoke Tests..."
mvn clean test -Psmoke
if [ $? -ne 0 ]; then
    echo "Smoke tests failed. Exiting..."
    exit 1
fi

# Run regression tests
echo "Running Regression Tests..."
mvn clean test -Pregression
if [ $? -ne 0 ]; then
    echo "Regression tests failed. Exiting..."
    exit 1
fi

# Run e2e tests
echo "Running E2E Tests..."
mvn clean test -Pe2e
if [ $? -ne 0 ]; then
    echo "E2E tests failed. Exiting..."
    exit 1
fi

echo "All tests passed successfully."
