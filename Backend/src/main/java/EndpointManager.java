import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

// @Singleton
public class EndpointManager {

    private static final int PORT = 8989; // Accept as config?
    private static final int MAX_THREADS = 6; // Accept as config.

    private static final String PATH_NEW_BOOKING = "/new/booking";

    private static final String PATH_CHECK_BOOKING_STATUS = "/status";

    ////// ******** Vendor Side *********** ///////////

    private static final String PATH_GET_ALL_BOOKINGS = "/get/all";


    private static final String PATH_REMOVE_BOOKING = "/del";

    private static final String PATH_FORCE_ADD_BOOKING = "/force/new";

    private static final String PATH_CONFIRM_BOOKING = "/confirm";

    private static final String PATH_ADD_PRICE_LIST = "/new/prices";

    private static final String PATH_GET_PRICE_LIST = "/price";



    private static final Logger logger = LoggerFactory.getLogger(EndpointManager.class.getName());

    private static EndpointManager endpointManager = null;

    public static EndpointManager getEndpointManager() {
        if (endpointManager == null) {
            endpointManager = new EndpointManager();
        }
        return endpointManager;
    }

    private final EndpointHandler endpointHandler;

    private EndpointManager() {
        this.endpointHandler
                = new EndpointHandler(new DatabaseManager().getDatabaseHandler(), new CommunicationHandler());
    }

    public void exposeEndpoints() {
        Spark.port(PORT);
        Spark.threadPool(MAX_THREADS);
        logger.info(String.format("Started Spark Rest Server at port %s", PORT));

        // Expose APIs
        Spark.post(PATH_NEW_BOOKING, endpointHandler::handleCreateNewBooking);

        Spark.get(PATH_CHECK_BOOKING_STATUS, endpointHandler::handleCheckBookingStatus);

        Spark.get(PATH_GET_PRICE_LIST, endpointHandler::handleGetPriceList);

        Spark.put(PATH_CONFIRM_BOOKING, endpointHandler::handleConfirmBooking);

        Spark.get(PATH_GET_ALL_BOOKINGS, endpointHandler::handleGetAllBookings);

        Spark.delete(PATH_REMOVE_BOOKING,endpointHandler::handleDeleteBooking);

        Spark.post(PATH_FORCE_ADD_BOOKING, endpointHandler::handleForceAddBooking);

        Spark.post(PATH_ADD_PRICE_LIST, endpointHandler::handleUpdatePrices);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        EndpointManager endpointManager = EndpointManager.getEndpointManager();
        endpointManager.exposeEndpoints();
    }

}
