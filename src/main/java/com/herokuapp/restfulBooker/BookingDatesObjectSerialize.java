package com.herokuapp.restfulBooker;

public class BookingDatesObjectSerialize {

    // Create private variables for each json field
    private String checkin;
    private String checkout;

    // Create a public constructor using declared fields
    public BookingDatesObjectSerialize(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // Generate toString() method
    @Override
    public String toString() {
        return "BookingDatesObjectSerialize{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }


    // Generate Getters and Setters
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

}
