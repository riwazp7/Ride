package booking;

import jdk.internal.joptsimple.internal.Strings;
import org.apache.commons.lang.RandomStringUtils;

public class BookingsUtil {

    public static String generateRandomBookingID() {
        return RandomStringUtils.random(6, "0126789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public static boolean validateBooking(Booking booking) {
        if (Strings.isNullOrEmpty(booking.getBookingID())
                || Strings.isNullOrEmpty(booking.getEmail())) {
            return false;
        }
        return true;
    }
}
