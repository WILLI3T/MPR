package com.example.MPR;

import com.example.MPR.dtos.Car;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyRestAssuredControllerTest {
    private static final String BASE_URL = "http://localhost:8080/api";


    @Test
    public void testGetCar(){
        when()
                .get(BASE_URL + "/cars/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("name", equalTo("test"))
                .log()
                .body();
    }
    @Test
    public void testGetCar2(){
        Car car = when()
                .get(BASE_URL + "/cars/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Car.class);

        assertEquals(1, car.getId());
        assertEquals("test", car.getName());
    }
    @Test
    public void testPostCar(){
        with()
                .body(new Car("test", 1))
                .contentType("application/json")
                .post(BASE_URL + "/cars")
                .then()
                .assertThat()
                .body("id", greaterThan(3))
                .body("name", equalTo("test"))
                .statusCode(201)
                .log()
                .body();


    }
}
