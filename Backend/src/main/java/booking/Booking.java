package booking;

import java.lang.reflect.Field;
import java.util.Date;

public class Booking {

    public static BookingBuilder newBuilder() {
        return new BookingBuilder();
    }

    // Implementation Details
    /**
     * A version number for the storage semantics.
     * Might be useful if we have to do major unexpected schema changes.
     * Look for other potential options.
     */
    private String SCHEMA_VERSION = "1.0";

    // Server side details
    /**
     * A unique id for this booking.
     */
    private String bookingID;

    /**
     * Check date is valid on client side, server side, or both.
     */
    private Date bookingDate;

    /**
     * If this ride has been manually confirmed by the vendor and is good to go.
     */
    private boolean isConfirmed;

    /**
     * IDs of other bookings that share this ride/booking.
     * Optional.
     */
    private String[] shareID; // ?

    /**
     * The price this booking was confirmed for.
     */
    private String price;

    // Client Mandatory Details
    /**
     * Origin address or dorm/williams building.
     */
    private String origin;

    /**
     * Destination address or dorm/williams building.
     */
    private String destination;

    /**
     * Exact date and time the ride is requested for.
     */
    private Date rideDate; // And time

    /**
     * Name
     */
    private String name;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String email;

    /**
     * If this ride is shared.
     */
    private boolean isShared;

    /**
     * Number of riders booked for this ride, if the ride is shared.
     */
    private int numRiders; // if shared. Some bound required.

    // Client Optional Details

    /**
     * Details of flight/train the client is taking the ride to, if applicable.
     * Optional.
     */
    private String flightDetails;

    /**
     * Additional information that can be entered by the client.
     * Optional.
     */
    private String comments;

    Booking(
            String bookingID,
            Date bookingDate,
            boolean isConfirmed,
            String[] shareID,
            String price,
            String origin,
            String destination,
            Date rideDate,
            String name,
            String phone,
            String email,
            boolean isShared,
            int numRiders,
            String flightDetails,
            String comments) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.isConfirmed = isConfirmed;
        this.shareID = shareID;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.rideDate = rideDate;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isShared = isShared;
        this.numRiders = numRiders;
        this.flightDetails = flightDetails;
        this.comments = comments;
    }

    // Remove in prod lol.
    @Override
    public String toString() {
        String toString = "";
        try {
            for (Field field : Booking.class.getDeclaredFields()) {
                toString += field.get(this).toString();
            }
        } catch (IllegalAccessException e) {
            //
        }
        return toString;
    }
}
