import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropServer {

    private final static Logger logger = LoggerFactory.getLogger(DropServer.class.getSimpleName());

    public static void main(String[] args) {
        while(true) {
            try {
                DropServer.newServer().start();
                logger.info("Started Drop Server");
            } catch (RuntimeException e) {
                logger.error("Fatal: Application stopped with an error", e);
            }
        }
    }

    public static DropServer newServer() {
        return new DropServer();
    }

    private DropServer() {
    }

    public void start() {

    }
}
