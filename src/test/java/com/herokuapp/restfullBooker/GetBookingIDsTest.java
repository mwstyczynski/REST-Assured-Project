package com.herokuapp.restfullBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetBookingIDsTest {

    @Test
    public void getBookingIDsWithoutFilterTest() {
//        Get response with booking IDs
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

//        Verify that response it 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

//        Verify at least 1 booking id in response
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking IDs is empty");
    }
}
