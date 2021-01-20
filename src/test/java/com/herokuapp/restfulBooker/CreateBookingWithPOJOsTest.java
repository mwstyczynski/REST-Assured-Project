package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingWithPOJOsTest extends BaseTest {

    @Test
    public void postBookingWithPOJOsTest() {

//      Create RESPONSE BODY with BookingObjectSerialize & BookingDatesObjectSerialize (this one 1st as it's used as variable value for 2nd one)
        BookingDatesObjectSerialize dates = new BookingDatesObjectSerialize("2021-01-01", "2022-01-01");
        BookingObjectSerialize serializedBody = new BookingObjectSerialize("John", "Brown", 666, false, dates, "cocaine");

//      Create RestAssured response
        Response response = RestAssured.given(spec).
//          auth().preemptive().basic("admin", "password123").
            contentType(ContentType.JSON).
            body(serializedBody).post("/booking");

        response.print();

//      Verifications
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200.\n");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getString("booking.firstname"), "John");
        softAssert.assertEquals(response.jsonPath().getString("booking.lastname"), "Brown");
        softAssert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 666);
        softAssert.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"), false);
        // as we are referring to another object inside main body we have to specify the path ('folders' dot-separated)
        softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), "2021-01-01");
        softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), "2022-01-01");
        softAssert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), "cocaine");

        softAssert.assertAll();

    }


}
