package com.herokuapp.restfulBooker;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTest extends BaseTest{

    @Test
    public void healthCheckTest() {
        given(spec).
            when().get("/ping").
            then().assertThat().statusCode(201);
    }
}
