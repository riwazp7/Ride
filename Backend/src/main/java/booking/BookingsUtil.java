package booking;

import org.apache.commons.lang.RandomStringUtils;

public class BookingsUtil {

    public static String generateRandomBookingID() {
        return RandomStringUtils.random(6, "0126789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }
}
