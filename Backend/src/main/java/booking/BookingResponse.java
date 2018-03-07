package booking;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

public class BookingResponse {

    private String SCHEMA_VERSION = "1.0";

    @Nonnull
    private String bookingID;

    private Date bookingDate;

    private boolean isConfirmed;

    private String price;

    private String origin;

    private String destination;

    private Date rideDate; // And time

    private String name;

    private String phone;

    private String email;

    private boolean isShared;

    private int numRiders; // if shared. Some bound required.

    @Nullable
    private String comments;

    public BookingResponse(Booking booking) {

    }

}

