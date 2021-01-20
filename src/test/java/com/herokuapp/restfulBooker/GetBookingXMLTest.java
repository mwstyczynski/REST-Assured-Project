package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingXMLTest extends BaseTest {

    @Test
    public void getBookingXMLTest() {
        // Create booking
        Response responseCreate = createBooking("Monica");
        responseCreate.print();

        // Set path variable
        responseCreate.jsonPath().getInt("bookingid");


        // Create and add XML header
        Header xml = new Header("Accept", "application/xml");
        spec.header(xml);

        // Get response
        spec.pathParam("bookingid", responseCreate.jsonPath().getInt("bookingid"));
        Response response = RestAssured.given(spec).get("/booking/{bookingid}");
        response.print();
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        SoftAssert softAssert = new SoftAssert();

        //Change jsonPath to xmlPath as we changed response type with additional header
        softAssert.assertEquals(response.xmlPath().getString("booking.firstname"), "Monica");
        softAssert.assertEquals(response.xmlPath().getString("booking.lastname"), "Brown");
        softAssert.assertEquals(response.xmlPath().getInt("booking.totalprice"), 666);
        softAssert.assertEquals(response.xmlPath().getBoolean("booking.depositpaid"), false);
        // As we are referring to another object inside main body we have to specify the path ('folders' dot-separated)
        softAssert.assertEquals(response.xmlPath().getString("booking.bookingdates.checkin"), "2021-01-01");
        softAssert.assertEquals(response.xmlPath().getString("booking.bookingdates.checkout"), "2022-01-01");
        softAssert.assertEquals(response.xmlPath().getString("booking.additionalneeds"), "cocaine");

        softAssert.assertAll();
    }
}
