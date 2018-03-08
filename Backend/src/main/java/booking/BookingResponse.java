package booking;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BookingResponse {

    @Nonnull
    private String bookingID;
    private String bookingDate;
    private boolean isConfirmed;
    private String price;
    private String origin;
    private String destination;
    private String rideDate; // And time?
    private String name;
    private String phone;
    private String email;
    private boolean isShared;
    private int numRiders;
    @Nullable private String comments;

    public BookingResponse(Booking booking) {
        this.bookingID = booking.getBookingID();
        this.bookingDate = booking.getBookingDate();
        this.isConfirmed = booking.isConfirmed();
        this.price = booking.getPrice();
        this.origin = booking.getOrigin();
        this.destination = booking.getDestination();
        this.rideDate = booking.getRideDate();
        this.name = booking.getName();
        this.phone = booking.getPhone();
        this.email = booking.getEmail();
        this.isShared = booking.isShared();
        this.numRiders = booking.getNumRiders();
        this.comments = booking.getComments();
    }
}

