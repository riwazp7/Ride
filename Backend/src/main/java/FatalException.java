/**
 * We throw a FatalException to crash the server by circumventing the top level try catch clause for all other
 * runtime exceptions.
 */
public class FatalException extends RuntimeException {
    FatalException(Exception e) {
        super(e);
    }

    FatalException(String e) {
        super(e);
    }
}
