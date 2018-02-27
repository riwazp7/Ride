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

    public BookingRequest(
            String price,
            String origin,
            String destination,
            Date rideDate,
            String name,
            String phone,
            String email,
            int numRiders,
            String flightDetails,
            String comments) {
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.rideDate = rideDate;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.numRiders = numRiders;
        this.flightDetails = flightDetails;
        this.comments = comments;
    }
}
