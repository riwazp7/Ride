package booking;

import javax.annotation.Nullable;
import java.util.Date;

public class BookingRequest {

    private final String price;
    private final String origin;
    private final String destination;
    private final Date rideDate;
    private final String name;
    private final String phone; // Validate
    private final String email; // Validate
    private final int numRiders; // Validate
    @Nullable private final String comments;

    public String getPrice() {
        return price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getRideDate() {
        return rideDate;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getNumRiders() {
        return numRiders;
    }

    @Nullable
    public String getComments() {
        return comments;
    }

    public BookingRequest(
            String price,
            String origin,
            String destination,
            Date rideDate,
            String name,
            String phone,
            String email,
            int numRiders,
            String comments) {
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.rideDate = rideDate;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.numRiders = numRiders;
        this.comments = comments;
    }

    public Booking toBooking(String bookingID, Date bookingDate, boolean isConfirmed, String[] shareID) {
            return Booking.newBuilder()
                    .setBookingID(bookingID)
                    .setBookingDate(bookingDate)
                    .setIsConfirmed(isConfirmed)
                    .setName(name)
                    .setEmail(email)
                    .setShareID(shareID)
                    .setPrice(price)
                    .setOrigin(origin)
                    .setDestination(destination)
                    .setRideDate(rideDate)
                    .setPhone(phone)
                    .setEmail(email)
                    .setIsShared(numRiders > 1)
                    .setNumRiders(numRiders)
                    .setComments(comments)
                    .build();
    }
}
