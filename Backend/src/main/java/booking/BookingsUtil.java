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
    // This should be handled in the client side but the check is still needed to not crash the server.
    public static boolean validateBookingRequest(BookingRequest bookingRequest) {
        if (Strings.isNullOrEmpty(bookingRequest.getEmail())
                || bookingRequest.getNumRiders() > MAX_RIDERS
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
}
