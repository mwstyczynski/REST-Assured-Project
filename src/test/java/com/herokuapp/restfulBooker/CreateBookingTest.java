package com.herokuapp.restfulBooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTest extends BaseTest {

    @Test
    public void postBookingTest() {

        Response response = createBooking("John");
        response.print();

//      Verifications
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

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
