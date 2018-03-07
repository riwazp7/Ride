import booking.Booking;
import booking.BookingBuilder;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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

    DatabaseManager() {
        this.mongoClient
                = new MongoClient("localhost");
        this.mongoDatabase = mongoClient.getDatabase(MONGO_DB_NAME);
        createCollectionIfNotPresent(mongoDatabase, MONGO_BOOKINGS_COLLECTION);
        this.bookingsCollection = mongoDatabase.getCollection(MONGO_BOOKINGS_COLLECTION);
        this.gson = new Gson();
    }

    /**
     *
     * @param booking A valid booking with all required fields non-empty and non-null.
     */
    public void addBooking(Booking booking) {
        Document document = Document.parse(gson.toJson(booking));
        bookingsCollection.insertOne(document);
    }

    @Nullable
    public Booking retrieveBooking(String bookingID, String bookingEmail) {
        Document document = new Document("email", bookingEmail);
        document.append("bookingID", bookingID);

        MongoCursor<Document> booking = bookingsCollection.find(document).iterator();
        if (booking.hasNext()) {
            return gson.fromJson(booking.next().toJson(), Booking.class);
        }
        return null;
    }

    public void confirmBooking(String bookingID) {
        Document document = new Document("bookingID", bookingID);
        Document updateQuery = new Document("$set", new Document("isConfirmed", true));
        bookingsCollection.updateOne(document, updateQuery);
    }

    // bookingId can possibly collide :/ Maybe generate longer IDs on the server side.
    public boolean deleteBooking(String bookingID, String email) {
        BasicDBObject toDelete = new BasicDBObject();
        toDelete.put("email", email);
        toDelete.put("bookingID", bookingID);
        bookingsCollection.deleteOne(toDelete);
        return true; // ?
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
