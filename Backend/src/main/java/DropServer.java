public class DropServer {

    public static void main(String[] args) {
        while(true) {
            try {
                DropServer.newServer().start();
            } catch (RuntimeException e) {
                //
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
