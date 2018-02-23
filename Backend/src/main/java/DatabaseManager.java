import booking.Booking;
import booking.BookingBuilder;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {

    private static final String MONGO_DB_NAME = "DB_DROP";

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final Gson gson;

    public DatabaseManager() {
        this.mongoClient
                = new MongoClient("localhost");
        this.mongoDatabase = mongoClient.getDatabase(MONGO_DB_NAME);
        this.gson = new Gson();
    }

    public void addBooking(Booking booking) {

    }

    public void retrieveBooking(String bookingID) {

    }

    public void confirmBooking(String bookingID) {

    }

    public void deleteBooking(String bookingID) {

    }

    public static void main(String[] args) {
        Booking booking = new BookingBuilder()
                .setName("Riwaz")
                .setEmail("rp7")
                .setBookingID("aezakmi")
                .setIsShared(false)
                .build();
        String test = new Gson().toJson(booking);
        System.out.println(test);
    }

}
