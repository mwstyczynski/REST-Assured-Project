package com.herokuapp.restfullBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBookingTest extends BaseTest {

    @Test
    public void patchUpdate(){
        Response responseCreate = createBooking("Andrew");
        responseCreate.print();
        Assert.assertEquals(responseCreate.getStatusCode(), 200, "Status code is not 200");
        int bookingID = responseCreate.jsonPath().getInt("bookingid");
        System.out.println("Created booking ID: " + bookingID);

        JSONObject patchedBody = new JSONObject();
        patchedBody.put("firstname", "Arthur");
        JSONObject patchedBookingDates = new JSONObject();
        patchedBookingDates.put("checkin", "2021-05-05");
        patchedBody.put("bookingdates", patchedBookingDates);

        Response patchedResponse = RestAssured.given().
                // in accordance with documentation we need authentication for out PUT method so we add few lines after GIVEN().
                        auth().preemptive().basic("admin", "password123").
                        contentType(ContentType.JSON).
                        body(patchedBody.toString()).patch("https://restful-booker.herokuapp.com/booking/" + bookingID);

        patchedResponse.print();


//      Verification of patch
        Assert.assertEquals(patchedResponse.getStatusCode(), 200, "Status code is not 200");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(patchedResponse.jsonPath().getString("firstname"), "Arthur");
        softAssert.assertEquals(patchedResponse.jsonPath().getString("lastname"), "Brown");
        softAssert.assertEquals(patchedResponse.jsonPath().getInt("totalprice"), 666);
        softAssert.assertEquals(patchedResponse.jsonPath().getBoolean("depositpaid"), false);
        // as we are referring to another object inside main body we have to specify the path ('folders' dot-separated)
        softAssert.assertEquals(patchedResponse.jsonPath().getString("bookingdates.checkin"), "2021-05-05");
        softAssert.assertEquals(patchedResponse.jsonPath().getString("bookingdates.checkout"), "2022-01-01");
        softAssert.assertEquals(patchedResponse.jsonPath().getString("additionalneeds"), "cocaine");

        softAssert.assertAll();

    }
}
