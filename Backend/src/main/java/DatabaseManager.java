import booking.Booking;
import booking.BookingBuilder;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.annotation.Nullable;

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
        // Add more and return a meaningful error.
        if (Strings.isNullOrEmpty(booking.getBookingID()) || Strings.isNullOrEmpty(booking.getEmail())) {
            return false;
        }
        try {
            Document document = Document.parse(gson.toJson(booking));
            bookingsCollection.insertOne(document);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Nullable
    public Booking retrieveBooking(String bookingID, String bookingEmail) {
        BasicDBObject queryObject = new BasicDBObject();
        queryObject.put("email", bookingEmail);
        queryObject.put("bookingID", bookingID);
        for (Document document : bookingsCollection.find(queryObject)) {
            return gson.fromJson(document.toJson(), Booking.class);
        }
        return null;
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
