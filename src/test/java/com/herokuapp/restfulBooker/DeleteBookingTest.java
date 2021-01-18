package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseTest {

    @Test
    public void deleteBooking(){
        Response response = createBooking("RubberBandMan");
        response.print();
        int bookingID = response.jsonPath().getInt("bookingid");

        Response delete = RestAssured.given().auth().preemptive().basic("admin", "password123").
                delete("https://restful-booker.herokuapp.com/booking/" + bookingID);
        Assert.assertEquals(delete.print(), "Created", "Booking was not deleted");
        Assert.assertEquals(delete.getBody().asString(), "Created", "Booking was not deleted");

        Assert.assertEquals(delete.getStatusCode(), 201, "Delete not successful.");

        Response validate = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingID);
        Assert.assertEquals(validate.print(), "Not Found", "Booking can still be found");
        Assert.assertEquals(validate.getBody().asString(), "Not Found", "Booking can still be found");
    }
}
