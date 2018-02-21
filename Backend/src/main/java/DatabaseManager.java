import booking.Booking;
import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class DatabaseManager {

    private static final String MONGO_DB_NAME = "DB_DROP";
    private static final String BOOKINGS_COLLECTION = "BOOKINGS";
    private static final CodecRegistry pojoRegistry = CodecRegistries.fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));


    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final Gson gson;

    public DatabaseManager() {
        this.mongoClient
                = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoRegistry).build());
        this.mongoDatabase = mongoClient.getDatabase(MONGO_DB_NAME);
        mongoDatabase.withCodecRegistry(pojoRegistry);
        this.gson = new Gson();
    }

    public void test() {
        Booking booking = Booking.newBuilder()
                .setEmail("test@xyza.com")
                .setName("MOM")
                .setComments("this is not a java comment")
                .build();
        mongoDatabase.getCollection(BOOKINGS_COLLECTION).drop();
        mongoDatabase.createCollection(BOOKINGS_COLLECTION);
        MongoCollection<Booking> collection = mongoDatabase.getCollection(BOOKINGS_COLLECTION, Booking.class);
        // System.out.println(gson.toJson(booking));
        collection.insertOne(booking);
        collection.find().forEach(new Block<Booking>() {
            @Override
            public void apply(Booking booking) {
                System.out.println(gson.toJson(booking));
            }
        });

    }

    public static void main(String[] args) {
        new DatabaseManager().test();
    }

}
