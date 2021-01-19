package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTest extends BaseTest {

    @Test
    public void putBookingTest() {

        // Create booking
        Response responseCreate = createBooking("John");
        responseCreate.print();
        // Get ID of newly created booking
        int bookingID = responseCreate.jsonPath().getInt("bookingid");
        System.out.println("Created booking ID: " + bookingID);


        //      UPDATE BOOKING
//      Create NEW UPDATED json bodies
        JSONObject updatedBody = new JSONObject();
        updatedBody.put("firstname", "Arthur");
        updatedBody.put("lastname", "Goyle");
        updatedBody.put("totalprice", 754);
        updatedBody.put("depositpaid", true);
        JSONObject updatedBookingDates = new JSONObject();
        updatedBookingDates.put("checkin", "2021-05-05");
        updatedBookingDates.put("checkout", "2022-05-05");
        updatedBody.put("bookingdates", updatedBookingDates);
        updatedBody.put("additionalneeds", "heroine");

//      To get the updated response body of previously created record we put bookingID parameter
//      into path String for PUT method and print updated response
        Response updatedResponse = RestAssured.given(spec).
        // in accordance with documentation we need authentication for out PUT method so we add few lines after GIVEN().
                auth().preemptive().basic("admin", "password123").
                contentType(ContentType.JSON).
                body(updatedBody.toString()).put("/booking/" + bookingID);

        updatedResponse.print();


//      Verifications
        Assert.assertEquals(updatedResponse.getStatusCode(), 200, "Status code is not 200");

        SoftAssert softAssert = new SoftAssert();
        // One part of path removed as updated response body is not in booking json object
        softAssert.assertEquals(updatedResponse.jsonPath().getString("firstname"), "Arthur");
        softAssert.assertEquals(updatedResponse.jsonPath().getString("lastname"), "Goyle");
        softAssert.assertEquals(updatedResponse.jsonPath().getInt("totalprice"), 754);
        softAssert.assertEquals(updatedResponse.jsonPath().getBoolean("depositpaid"), true);
        // As we are referring to another object inside main body we have to specify the path ('folders' dot-separated)
        softAssert.assertEquals(updatedResponse.jsonPath().getString("bookingdates.checkin"), "2021-05-05");
        softAssert.assertEquals(updatedResponse.jsonPath().getString("bookingdates.checkout"), "2022-05-05");
        softAssert.assertEquals(updatedResponse.jsonPath().getString("additionalneeds"), "heroine");

        softAssert.assertAll();

    }


}
