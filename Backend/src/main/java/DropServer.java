import org.apache.commons.mail.EmailException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class DropServer {

    private final static Logger logger = LoggerFactory.getLogger(DropServer.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        // log4j basic configuration; replace later
        BasicConfigurator.configure();
        LogManager.getRootLogger().setLevel(Level.INFO);

        while(true) { // Crashing after some error is better.
            try {
                DropServer.newServer().start();
                logger.info("Started Drop Server");
                Thread.sleep(Long.MAX_VALUE);
            } catch (RuntimeException e) {
                if (e instanceof FatalException) {
                    logger.error("SERVER CRASHED WITH A FATAL ERROR: ", e);
                    sendCrashNotification(Arrays.toString(e.getStackTrace()));
                    break;
                }
                logger.error("Server stopped with an error: ", e);
                logger.error("Attempting Restart");
            }
        }
    }

    public static DropServer newServer() {
        return new DropServer(EndpointManager.getEndpointManager(), new DatabaseManager());
    }

    private final EndpointManager endpointManager;
    private final DatabaseManager databaseManager;

    private DropServer(EndpointManager endpointManager, DatabaseManager databaseManager) {
        this.endpointManager = endpointManager;
        this.databaseManager = databaseManager;
    }

    public void start() {
        //databaseManager.

        // Expose as the last step
        endpointManager.exposeEndpoints();
    }

    private static void sendCrashNotification(String info) {
        for (String toAddress : Params.CRASH_NOTIFICATION_EMAILS) {
            try {
                CommunicationHandler.sendEmail("DROP SERVER HAS CRASHED", info, toAddress);
            } catch (EmailException e) {
                logger.error(String.format("Sending crash notification to %s failed", toAddress), e);
            }
        }
    }
}
