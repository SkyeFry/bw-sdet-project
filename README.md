# Skye Fry SDET Project

## Description

This project is a test automation framework for the SDET_Assessment project. The framework is built using Java,
Playwright, JUnit, and Maven. The tests are written in Java and use JUnit as the test runner. Playwright is used to
interact with the browser. Maven is used to manage the project dependencies and run the tests.

## Table of Contents

- [Installation](#installation)
- [Running Tests](#running-tests)

## Installation

Instructions on how to set up the project locally.

Note: You will need to have Maven and Java JDK installed on your machine and added to your PATH.

```bash
# Clone the repository
git clone https://github.com/SkyeFry/bw-sdet-project.git

# Navigate to the project directory
cd bw-sdet-project ## replace with the path to the project directory

# Install dependencies
mvn install

# Create the .env file
touch .env

# Add the following content to the .env file
echo "BROWSER_NAME=chrome" >> .env
echo "HEADLESS=true" >> .env
echo "BASE_URL=http://localhost:8080/" >> .env
```

## Running Tests

```bash
# Run all tests
mvn test
```
