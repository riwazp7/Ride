import booking.Booking;
import booking.BookingBuilder;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseManager {

    private static final String MONGO_DB_NAME = "DB_DROP";
    private static final String MONGO_BOOKINGS_COLLECTION = "BOOKINGS_COLLECTION";

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> bookingsCollection;
    private final Gson gson;

    public DatabaseManager() {
        this.mongoClient
                = new MongoClient("localhost");
        this.mongoDatabase = mongoClient.getDatabase(MONGO_DB_NAME);
        createCollectionIfNotPresent(mongoDatabase, MONGO_BOOKINGS_COLLECTION);
        this.bookingsCollection = mongoDatabase.getCollection(MONGO_BOOKINGS_COLLECTION);
        this.gson = new Gson();
    }

    public boolean addBooking(Booking booking) {

        try {
            Document document = Document.parse(gson.toJson(booking));
            bookingsCollection.insertOne(document);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Booking retrieveBooking(String bookingID, String bookingEmail) {
        return Booking.newBuilder().setName("test").setBookingID(bookingID).setEmail(bookingEmail).build();
    }

    public boolean confirmBooking(String bookingID) {
        return false;
    }

    public boolean deleteBooking(String bookingID) {
        return false;
    }

    private static void createCollectionIfNotPresent(MongoDatabase db, String collection) {
        for (String c : db.listCollectionNames()) {
            if (c.equals(collection)) {
                return;
            }
        }
        db.createCollection(collection);
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
