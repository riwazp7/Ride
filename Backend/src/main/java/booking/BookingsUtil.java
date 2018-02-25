package booking;

import org.apache.commons.lang.RandomStringUtils;

public class BookingsUtil {

    public static String generateRandomBookingID() {
        return RandomStringUtils.random(6, "0126789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generateRandomBookingID());
        }
    }

}
