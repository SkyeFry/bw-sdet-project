package org.example.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8081";
        ensureWeatherForecastExists();
    }

    protected void ensureWeatherForecastExists() {
        Response response = RestAssured.given()
                .when()
                .get("/weatherforecast");

        if (response.jsonPath().getList("$").isEmpty()) {
            String requestBody = "{ \"date\": \"2023-10-01\", \"temperatureC\": 20, \"summary\": \"Mild\", \"temperatureF\": 67 }";

            RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .when()
                    .post("/weatherforecast")
                    .then()
                    .statusCode(201);
        }
    }
}
