import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropServer {

    private final static Logger logger = LoggerFactory.getLogger(DropServer.class.getSimpleName());

    public static void main(String[] args) throws Exception {

        // log4j basic configuration; replace later
        BasicConfigurator.configure();
        while(true) {
            try {
                DropServer.newServer().start();
                logger.info("Started Drop Server");
                Thread.sleep(Long.MAX_VALUE);
            } catch (RuntimeException e) {
                logger.error("SERVER STOPPED WITH A FATAL ERROR", e);
            }
        }
    }

    public static DropServer newServer() {
        return new DropServer(EndpointManager.getEndpointManager(), new StateManager());
    }

    private final EndpointManager endpointManager;
    private final StateManager stateManager;

    private DropServer(EndpointManager endpointManager, StateManager stateManager) {
        this.endpointManager = endpointManager;
        this.stateManager = stateManager;
    }

    public void start() {
        stateManager.loadStateFromDisk();
        // Last step
        endpointManager.exposeEndpoints();
    }
}
