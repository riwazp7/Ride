package booking;

import java.util.HashMap;

public class BookingStore {

    /**
     * Memory copy of all current bookings.
     * This needs to be synced with disk copy bookings.
     * State is maintained in the disk and not in memory.
     */
    private HashMap<String, Booking> bookings;
}
