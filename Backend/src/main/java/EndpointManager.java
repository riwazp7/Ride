import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

// @Singleton
public class EndpointManager {

    private static final int PORT = 8989; // Accept as config

    private static final Logger logger = LoggerFactory.getLogger(EndpointManager.class.getSimpleName());

    private static EndpointManager endpointManager = null;

    public static EndpointManager getEndpointManager() {
        if (endpointManager == null) {
            endpointManager = new EndpointManager();
        }
        return endpointManager;
    }

    private EndpointManager() {

    }

    public void exposeEndpoints() {
        Spark.port(PORT);
        logger.info(String.format("Started Spark Rest Server at port %s", PORT));
        // Expose APIs
    }
}
