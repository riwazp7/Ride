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
                logger.error("Fatal: Application stopped with an error", e);
            }
        }
    }

    public static DropServer newServer() {
        return new DropServer(EndpointHandler.getEndpointHandler());
    }

    private final EndpointHandler endpointHandler;

    private DropServer(EndpointHandler endpointHandler) {
        this.endpointHandler = endpointHandler;
    }

    public void start() {
        endpointHandler.exposeAPIS();
    }
}
