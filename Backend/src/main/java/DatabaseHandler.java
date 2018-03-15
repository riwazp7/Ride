import booking.Booking;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DatabaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class.getSimpleName());

    private final MongoCollection<Document> bookingsCollection;
    private final MongoCollection<Document> pricesCollection;
    private final Gson gson = new Gson();

    public DatabaseHandler(MongoCollection<Document> bookingsCollection, MongoCollection<Document> pricesCollection) {
        this.bookingsCollection = bookingsCollection;
        this.pricesCollection = pricesCollection;
    }

    /**
     *
     * @param booking A valid booking with all required fields non-empty and non-null.
     * TODO: Basic validation here? Like check for duplicate.
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
                "Delete request for ID:%s, email:%s was not acknowledged by the database",
                bookingID,
                email));
        return false;
    }

    public String readPriceList() {
        return "";
    }

    public boolean setPrices() {
        return false;
    }
}
