import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class.getSimpleName());

    private static final String MONGO_DB_NAME = "DB_DROP";

    private static final String MONGO_BOOKINGS_COLLECTION = "BOOKINGS_COLLECTION";
    private static final String MONGO_PRICE_COLLECTION = "PRICE_COLLECTION";

    private final DatabaseHandler databaseHandler;

    DatabaseManager() {
        MongoDatabase database = new MongoClient("localhost").getDatabase(MONGO_DB_NAME);
        createCollectionIfNotPresent(database, MONGO_BOOKINGS_COLLECTION);
        createCollectionIfNotPresent(database, MONGO_PRICE_COLLECTION);
        this.databaseHandler = new DatabaseHandler(
                database.getCollection(MONGO_BOOKINGS_COLLECTION),
                database.getCollection(MONGO_PRICE_COLLECTION));
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    private static void createCollectionIfNotPresent(MongoDatabase db, String collection) {
        for (String c : db.listCollectionNames()) {
            if (c.equals(collection)) {
                logger.debug(String.format("Collection %s present. Skipped creating new one.", collection));
                return;
            }
        }
        logger.info(String.format("Collection %s not found. Created new one.", collection));
        db.createCollection(collection);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        DatabaseManager databaseManager = new DatabaseManager();
    }

}
