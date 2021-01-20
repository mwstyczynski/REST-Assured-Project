package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookingWithPOJOsDeserializeTest extends BaseTest {

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

//      Deserialization using empty constructors of classes
//      Getters and Setters are required for RestAssured response
//      to be able to parametrize where and what will go from response to our class as a declared variable
        BookingIdObjectSerialize bookingid = response.as(BookingIdObjectSerialize.class);

//      Print response that we posted
        System.out.println("Posted: " + serializedBody.toString());
//      Print response that we got
        System.out.println("Got: " + bookingid.getBooking().toString());

//      Verifications
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200.\n");
//      All verifications can be done based on class related response data
        Assert.assertEquals(bookingid.getBooking().toString(), serializedBody.toString());

    }

}
