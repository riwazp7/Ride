import booking.Booking;
import booking.BookingsUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.BasicConfigurator;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

public class DatabaseManager {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class.getSimpleName());

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
    public Booking retrieveBooking(String bookingID, String bookingEmail) throws JsonSyntaxException {
        Document document = new Document("email", bookingEmail);
        document.append("bookingID", bookingID);

        MongoCursor<Document> booking = bookingsCollection.find(document).iterator();
        if (booking.hasNext()) {
            return gson.fromJson(booking.next().toJson(), Booking.class);
        }
        return null;
    }

    @Nullable
    public Booking confirmBooking(String bookingID, String bookingEmail) {
        Booking booking = retrieveBooking(bookingID, bookingEmail);
        if (booking == null) {
            return null;
        }
        Document document = new Document("bookingID", bookingID);
        document.append("email", bookingEmail);
        Document updateQuery = new Document("$set", new Document("isConfirmed", true));
        bookingsCollection.updateOne(document, updateQuery);
        return booking;
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
        BasicConfigurator.configure();
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.addBooking(BookingsUtil.getTestBookingA());
        databaseManager.addBooking(BookingsUtil.getTestBookingB());
    }

}
