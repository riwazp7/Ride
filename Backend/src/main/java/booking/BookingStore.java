package booking;

import java.util.HashMap;

public class BookingStore {

    /**
     * Memory copy of all current bookings.
     * This needs to be synced with disk copy bookings.
     */
    private HashMap<String, Booking> bookings;
}
