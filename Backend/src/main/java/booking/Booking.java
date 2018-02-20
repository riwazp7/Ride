package booking;

import java.util.Date;

public class Booking {

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

    private Booking(
            String SCHEMA_VERSION,
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
        this.SCHEMA_VERSION = SCHEMA_VERSION;
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

    public static class BookingBuilder {
        private String schema_version;
        private String bookingID;
        private Date bookingDate;
        private boolean isConfirmed;
        private String[] shareID;
        private String price;
        private String origin;
        private String destination;
        private Date rideDate;
        private String name;
        private String phone;
        private String email;
        private boolean isShared;
        private int numRiders;
        private String flightDetails;
        private String comments;

        public BookingBuilder setSCHEMA_VERSION(String schema_version) {
            this.schema_version = schema_version;
            return this;
        }

        public BookingBuilder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public BookingBuilder setBookingDate(Date bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public BookingBuilder setIsConfirmed(boolean isConfirmed) {
            this.isConfirmed = isConfirmed;
            return this;
        }

        public BookingBuilder setShareID(String[] shareID) {
            this.shareID = shareID;
            return this;
        }

        public BookingBuilder setPrice(String price) {
            this.price = price;
            return this;
        }

        public BookingBuilder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public BookingBuilder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public BookingBuilder setRideDate(Date rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public BookingBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BookingBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public BookingBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public BookingBuilder setIsShared(boolean isShared) {
            this.isShared = isShared;
            return this;
        }

        public BookingBuilder setNumRiders(int numRiders) {
            this.numRiders = numRiders;
            return this;
        }

        public BookingBuilder setFlightDetails(String flightDetails) {
            this.flightDetails = flightDetails;
            return this;
        }

        public BookingBuilder setComments(String comments) {
            this.comments = comments;
            return this;
        }

        public Booking build() {
            return new Booking(
                    schema_version,
                    bookingID,
                    bookingDate,
                    isConfirmed,
                    shareID,
                    price,
                    origin,
                    destination,
                    rideDate,
                    name,
                    phone,
                    email,
                    isShared,
                    numRiders,
                    flightDetails,
                    comments);
        }
    }
}
