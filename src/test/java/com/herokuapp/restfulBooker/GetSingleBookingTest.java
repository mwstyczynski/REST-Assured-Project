package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetSingleBookingTest extends BaseTest {

    @Test
    public void getSingleIdTest(){

        Response responseCreate = createBooking("Monica");
        responseCreate.print();
        responseCreate.jsonPath().getInt("bookingid");

        spec.pathParam("bookingid", responseCreate.jsonPath().getInt("bookingid"));
        Response response = RestAssured.given(spec).get("/booking/{bookingid}");
        response.print();
        System.out.println("Status code is: "+ response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

//        Soft Assert allows test to continue and to assertAll at the end of the test
//        That's how we can continue the test even if one value is failing - rest of that will be validated
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.jsonPath().getString("firstname"), "Susan");
        softAssert.assertEquals(response.jsonPath().getString("lastname"), "Ericsson");
        softAssert.assertEquals(response.jsonPath().getInt("totalprice"), 768);
        softAssert.assertEquals(response.jsonPath().getBoolean("depositpaid"), false);
        // As we are referring to another object inside main body we have to specify the path ('folders' dot-separated)
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2020-07-25");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "2020-10-06");
        softAssert.assertEquals(response.jsonPath().getString("additionalneeds"), "Breakfast");

        softAssert.assertAll();

// jsonPath from restAssured is different than jsonPath from Jayway so search for specific syntax
//        https://www.javadoc.io/doc/io.rest-assured/json-path/3.0.0/io/restassured/path/json/JsonPath.html
    }
}

//  Response body (changes all the time, so values can be different on each test run)

//          {
//        "firstname": "Sally",
//        "lastname": "Brown",
//        "totalprice": 111,
//        "depositpaid": true,
//        "bookingdates": {
//        "checkin": "2013-02-23",
//        "checkout": "2014-10-23"
//        },
//        "additionalneeds": "Breakfast"
//        }
