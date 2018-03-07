package booking;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;

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
    @Nonnull private String bookingID;

    /**
     * Check date is valid on client side, server side, or both.
     */
    private long bookingDate;

    /**
     * If this ride has been manually confirmed by the vendor and is good to go.
     */
    private boolean isConfirmed;

    /**
     * IDs of other bookings that share this ride/booking.
     * Optional.
     */
    @Nullable
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
    private long rideDate; // And time

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
     * Additional information that can be entered by the client.
     * Optional.
     */
    @Nullable
    private String comments;

    public String SCHEMA_VERSION() {
        return SCHEMA_VERSION;
    }

    public String getBookingID() {
        return bookingID;
    }

    public long getBookingDate() {
        return bookingDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public String[] getShareID() {
        return shareID;
    }

    public String getPrice() {
        return price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public long getRideDate() {
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

    public boolean isShared() {
        return isShared;
    }

    public int getNumRiders() {
        return numRiders;
    }

    public String getComments() {
        return comments;
    }

    Booking(
            String bookingID,
            long bookingDate,
            boolean isConfirmed,
            String[] shareID,
            String price,
            String origin,
            String destination,
            long rideDate,
            String name,
            String phone,
            String email,
            boolean isShared,
            int numRiders,
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
        this.comments = comments;
    }

    @Override
    public String toString() {
        String toString = "";
        for (Field field : Booking.class.getDeclaredFields()) {
            try {
                toString += (field.getName() + ": " + field.get(this).toString() + "\n");
            } catch (IllegalAccessException | NullPointerException e) {
                //
            }
        }
        return toString;
    }
}
