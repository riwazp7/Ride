package booking;

import java.util.Date;

public class Booking {
    // Server side details
    private String bookingID;
    private Date bookingDate;
    private boolean isConfirmed;
    private String shareID; // ?


    // Client Mandatory Details
    private String origin;
    private String destination;
    private Date rideDate; // And time
    private String name;
    private String phone;
    private String email;
    private boolean isShared;
    private int numRiders; // if shared. Some bound required.

    // Client Optional Details
    private String flightDetails;
    private String comments;

}
