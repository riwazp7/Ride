import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

// @Singleton
public class EndpointHandler {

    private static final int PORT = 8989; // Accept as config

    private static final Logger logger = LoggerFactory.getLogger(EndpointHandler.class.getSimpleName());

    private static EndpointHandler endpointHandler = null;

    public static EndpointHandler getEndpointHandler() {
        if (endpointHandler == null) {
            endpointHandler = new EndpointHandler();
        }
        return endpointHandler;
    }

    private EndpointHandler() {

    }

    public void exposeAPIS() {
        Spark.port(PORT);
        logger.info(String.format("Started Spark Rest Server at port %s", PORT));
        // Expose APIs
    }
}
