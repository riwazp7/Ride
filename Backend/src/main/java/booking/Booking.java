package booking;

import java.util.Date;

public class Booking {

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
     * If this ride has been manually confirmed and is good to go.
     */
    private boolean isConfirmed;

    /**
     * An ID generated in the backend.
     * Optional.
     */
    private String shareID; // ?

    /**
     * The price this booking was confirmed for.
     */
    private String price;


    // Client Mandatory Details

    /**
     * Can be an address or a Williams dorm?
     */
    private String origin;

    /**
     *
     */
    private String destination;

    /**
     *
     */
    private Date rideDate; // And time

    /**
     *
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

}
