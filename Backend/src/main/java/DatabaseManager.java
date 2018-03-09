import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class.getSimpleName());

    private static final MongoClientURI uri
            = new MongoClientURI("mongodb+srv://riwazp7:amdisbetterthanintel!@cluster0-wqfk4.mongodb.net/test");

    private static final String MONGO_DB_NAME = "DB_DROP";
    private static final String MONGO_BOOKINGS_COLLECTION = "BOOKINGS_COLLECTION";

    private final DatabaseHandler databaseHandler;

    DatabaseManager() {
        MongoDatabase database = new MongoClient("localhost").getDatabase(MONGO_DB_NAME);
        createCollectionIfNotPresent(database, MONGO_BOOKINGS_COLLECTION);
        this.databaseHandler = new DatabaseHandler(database.getCollection(MONGO_BOOKINGS_COLLECTION));
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
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
    }

}
