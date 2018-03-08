package booking;

import com.google.common.base.Strings;
import org.apache.commons.lang.RandomStringUtils;

public class BookingsUtil {

    // Display message in the ui to call bob instead for rides with larger # of riders.
    private static final int MAX_RIDERS = 5;

    public static String generateRandomBookingID() {
        return RandomStringUtils.random(6, "0126789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    // Better way to do this?
    // Other logical checks? Like Origin != Dest, validate date and time, etc.
    // This should be checked in the client side too but the check is still needed to not crash the server.
    public static boolean validateBookingRequest(BookingRequest bookingRequest) {
        if (Strings.isNullOrEmpty(bookingRequest.getEmail())
                || bookingRequest.getNumRiders() > MAX_RIDERS || bookingRequest.getNumRiders() < 1
                || Strings.isNullOrEmpty(bookingRequest.getEmail())
                || Strings.isNullOrEmpty(bookingRequest.getName())
                || Strings.isNullOrEmpty(bookingRequest.getEmail())
                || Strings.isNullOrEmpty(bookingRequest.getPhone())
                || Strings.isNullOrEmpty(bookingRequest.getDestination())
                || Strings.isNullOrEmpty(bookingRequest.getOrigin())
                || Strings.isNullOrEmpty(bookingRequest.getPrice())) {
            // Check date for validity.
            return false;
        }
        return true;
    }

    // Move to test utils once we have one :)
    public static Booking getTestBookingA() {
        return Booking.newBuilder()
                .setDestination("Albany")
                .setOrigin("whereever")
                .setPrice("50")
                .setBookingID(generateRandomBookingID())
                .setBookingDate(System.currentTimeMillis() + "")
                .setRideDate(1520443565 + "")
                .setPhone("4133467873")
                .setComments("No comment")
                .setName("Riwaz")
                .setNumRiders(1)
                .setEmail("rp7@williams.edu")
                .build();
    }

    // Move to test utils once we have one :)
    public static Booking getTestBookingB() {
        return Booking.newBuilder()
                .setDestination("Nepal")
                .setOrigin("Kathmandu")
                .setPrice("60")
                .setBookingID(generateRandomBookingID())
                .setBookingDate(System.currentTimeMillis() + "")
                .setRideDate(1520543565 + "")
                .setPhone("4133467870")
                .setComments("Yes comment")
                .setName("Karan")
                .setNumRiders(1)
                .setEmail("rp7@williams.edu")
                .build();
    }
}
