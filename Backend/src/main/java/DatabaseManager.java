import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public DatabaseManager() {
        this.mongoClient = new MongoClient("localhost", 27017);
        this.mongoDatabase = mongoClient.getDatabase("test_ride");
    }



}
