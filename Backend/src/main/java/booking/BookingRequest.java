package booking;

import javax.annotation.Nullable;
import java.util.Date;

public class BookingRequest {

    private String price;
    private String origin;
    private String destination;
    private Date rideDate;
    private String name;
    private String phone; // Validate
    private String email; // Validate
    private int numRiders; // Validate
    @Nullable private String flightDetails;
    @Nullable private String comments;


}
