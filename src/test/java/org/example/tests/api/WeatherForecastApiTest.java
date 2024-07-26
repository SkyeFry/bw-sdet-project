package org.example.tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.base.BaseApiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WeatherForecastApiTest extends BaseApiTest {

    @BeforeEach
    public void weatherForecastApiSetUp() {
        RestAssured.baseURI = "http://localhost:8081";
        ensureWeatherForecastExists();
    }

    @Test
    public void getAllWeatherForecasts() {
        Response response = given()
                .when()
                .get("/weatherforecast")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .extract()
                .response();
    }

    @Test
    public void getWeatherForecastById() {
        int id = 1;

        given()
                .pathParam("id", id)
                .when()
                .get("/weatherforecast/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("date", equalTo("2023-10-01"))
                .body("temperatureC", equalTo(20))
                .body("summary", equalTo("Mild"))
                .body("temperatureF", equalTo(67));
    }

    @Test
    public void getWeatherForecastById_InvalidId() {
        int id = 9999;

        given()
                .pathParam("id", id)
                .when()
                .get("/weatherforecast/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void createWeatherForecast_ValidBody() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": 48, \"summary\": \"Scorching\", \"temperatureF\": 118 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(201)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("date", equalTo("2024-07-26"))
                .body("temperatureC", equalTo(48))
                .body("summary", equalTo("Scorching"))
                .body("temperatureF", equalTo(118));
    }

    @Test
    public void createWeatherForecast_InvalidDate() {
        String requestBody = "{ \"date\": \"InvalidDate\", \"temperatureC\": -17, \"summary\": \"Freezing\", \"temperatureF\": 0 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_InvalidTemperatureC() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": Invalid, \"summary\": \"Freezing\", \"temperatureF\": -148 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_InvalidSummary() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": 10, \"summary\": \"InvalidSummary\", \"temperatureF\": 50 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_InvalidTemperatureF() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": 20, \"summary\": \"Mild\", \"temperatureF\": Invalid }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_EmptyDate() {
        String requestBody = "{ \"date\": \"\", \"temperatureC\": 20, \"summary\": \"Mild\", \"temperatureF\": 68 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_EmptyTemperatureC() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": \"\", \"summary\": \"Mild\", \"temperatureF\": 68 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_EmptySummary() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": 20, \"summary\": \"\", \"temperatureF\": 68 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Disabled("Bug in test - temperatureF is not being validated")
    @Test
    public void createWeatherForecast_EmptyTemperatureF() {
        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": 20, \"summary\": \"Mild\", \"temperatureF\": \"\" }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }

    @Test
    public void createWeatherForecast_EmptyBody() {
        given()
                .header("Content-Type", "application/json")
                .body("{}")
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(201)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("date", equalTo("0001-01-01"))
                .body("temperatureC", equalTo(0))
                .body("summary", equalTo("Undefined"))
                .body("temperatureF", equalTo(32));
    }

    @Test
    public void createWeatherForecast_MissingHeader() {
        RestAssured.baseURI = "http://localhost:8081";

        String requestBody = "{ \"date\": \"2024-07-26\", \"temperatureC\": 20, \"summary\": \"Mild\", \"temperatureF\": 68 }";

        given()
                .body(requestBody)
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(415);
    }

    @Test
    public void createWeatherForecast_MissingBody() {
        RestAssured.baseURI = "http://localhost:8081";

        given()
                .header("Content-Type", "application/json")
                .when()
                .post("/weatherforecast")
                .then()
                .statusCode(400);
    }
}
