import booking.Booking;
import booking.BookingsUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.apache.log4j.BasicConfigurator;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DatabaseManager {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class.getSimpleName());

    private static final MongoClientURI uri
            = new MongoClientURI("mongodb+srv://riwazp7:amdisbetterthanintel!@cluster0-wqfk4.mongodb.net/test");

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

    public List<Booking> retrieveAll() {
        List<Booking> res = new ArrayList<>();
        bookingsCollection.find()
                .forEach((Consumer<Document>) document -> res.add(gson.fromJson(document.toJson(), Booking.class)));
        return res;
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

    public boolean deleteBooking(String bookingID, String email) {
        BasicDBObject toDelete = new BasicDBObject();
        toDelete.put("email", email);
        toDelete.put("bookingID", bookingID);
        DeleteResult deleteResult = bookingsCollection.deleteOne(toDelete);
        if (deleteResult.wasAcknowledged()) {
            logger.debug(String.format("Delete Booking request for ID:%s, email:%s succeeded", bookingID, email));
            return true; // ?
        }
        logger.error(String.format(
                "Delete request for ID:%s, email:%s was NOT acknowledged by the database",
                bookingID,
                email));
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
        BasicConfigurator.configure();
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.addBooking(BookingsUtil.getTestBookingA());
        databaseManager.addBooking(BookingsUtil.getTestBookingB());
    }

}
