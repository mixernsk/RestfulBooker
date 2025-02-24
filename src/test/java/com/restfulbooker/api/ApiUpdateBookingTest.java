package com.restfulbooker.api;

import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.BookingResponse;
import com.restfulbooker.api.payloads.lombok.Booking;
import com.restfulbooker.api.payloads.lombok.BookingDates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiUpdateBookingTest extends BaseTest {

    @Test
    public void updateBooking() {
        BookingDates dates = new BookingDates();
        Booking payload = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();
        Booking updatedPayload = payload.toBuilder()
                .firstname("John")
                .lastname("Constantine")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);
        int bookingId = createdBookingResponse.getBookingid();

        BookingApi.updateBooking(bookingId, updatedPayload, token).as(Booking.class);
        Booking responseGetBookingById = BookingApi.getBooking(bookingId, "application/json").as(Booking.class);
        assertEquals(updatedPayload, responseGetBookingById, "Received booking is not as expected");
    }
}
