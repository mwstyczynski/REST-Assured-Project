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

    // To be able to DESERIALIZE we need to create and empty constructor too
    public BookingDatesObjectSerialize() {
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

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

}
