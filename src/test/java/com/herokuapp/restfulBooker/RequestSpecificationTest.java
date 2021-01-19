package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest extends BaseTest{
//spec is described in BaseTest class as @BeforeMethod
    @Test
    public void requestSpec() {

        // With BDD syntax
        given().spec(spec).when().get("/ping").then().assertThat().statusCode(201);

        // With java syntax
        Response response = RestAssured.given(spec).get("/ping");
        Assert.assertEquals(response.getStatusCode(), 201,"Status code is not 200");
    }

}
