package booking;

// Have a single builder for Booking Request, Booking, and Booking Response?
public class BookingBuilder {
    private String bookingID;
    private long bookingDate;
    private boolean isConfirmed = false;
    private String[] shareID;
    private String price;
    private String origin;
    private String destination;
    private long rideDate;
    private String name;
    private String phone;
    private String email;
    private boolean isShared;
    private int numRiders = 1;
    private String comments = "";

    public BookingBuilder setBookingID(String bookingID) {
        this.bookingID = bookingID;
        return this;
    }

    public BookingBuilder setBookingDate(long bookingDate) {
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

    public BookingBuilder setRideDate(long rideDate) {
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

    public BookingBuilder setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Booking build() {
        return new Booking(
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
                comments);
    }
}
