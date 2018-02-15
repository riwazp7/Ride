import java.util.logging.Logger;

public class DropServer {

    private static final Logger logger = Logger.getLogger(DropServer.class.getSimpleName());

    public static void main(String[] args) {
        while(true) {
            try {
                DropServer.newServer().start();
            } catch (RuntimeException e) {
                logger.severe(e.toString());
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
