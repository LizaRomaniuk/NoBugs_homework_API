package org.example;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.example.api.UnicornRequests;
import org.example.api.models.Unicorn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class SimpleTest {
    @BeforeAll
    public static void setupTests() {
        // Отключение проверки SSL-сертификатов для всех запросов
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://crudcrud.com/api/315198f758904bc58b39bab6fdaab0ca";
        // Принцип программирования DRY DO NOT REPEAT YOURSELF

    }

    @Test
    public void userShoudBeAbleCreateUnicorn() {
        // сериализация из JSON в объект и наоборот
        Unicorn unicorn = new Unicorn("Masha","Red");

        UnicornRequests unicornRequests = new UnicornRequests();
        unicornRequests.createUnicorn(unicorn.toJson());
    }

    @Test
    public void userShouldBeAbleDeleteExitingUnicorn() {
        String id = UnicornRequests.createUnicorn("{\n" + "  \"name\": \"Masha\",\n" + "  \"tailcolor\": \"Red\"\n" + "}");
        UnicornRequests.deleteUnicorn(id);

        given()
        .when()
                .get("/unicorn/" + id)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void userShouldBeAbleChangeTailColorOfExitingUnicorn() {
        Unicorn unicorn = new Unicorn( "Masha",  "Red");
        String id = UnicornRequests.createUnicorn(unicorn.toJson());
        given()
                .body("{\n" + "  \"name\": \"Masha\",\n" + "  \"tailcolor\": \"Blue\"\n" + "}")
                .contentType(ContentType.JSON)
        .when()
                .put("/unicorn/" + id)
        .then()
                .assertThat()
                .statusCode(200);
    }
}
