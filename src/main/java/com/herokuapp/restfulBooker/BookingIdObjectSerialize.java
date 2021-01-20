package com.herokuapp.restfulBooker;

public class BookingIdObjectSerialize {

    private int bookingid;
    private BookingObjectSerialize booking;

    public BookingIdObjectSerialize(int bookingid, BookingObjectSerialize booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    //    To be able to DESERIALIZE we need to create and empty constructor too
    public BookingIdObjectSerialize() {
    }


    @Override
    public String toString() {
        return "BookingIdObjectSerialize{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingObjectSerialize getBooking() {
        return booking;
    }

    public void setBooking(BookingObjectSerialize booking) {
        this.booking = booking;
    }

}



/*
{
    "bookingid": 1,                     1st variable
    "booking": {                        2nd variable (name has to be inline. We got a class for that already)
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
 */