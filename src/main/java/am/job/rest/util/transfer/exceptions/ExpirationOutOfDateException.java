package am.job.rest.util.transfer.exceptions;

public class ExpirationOutOfDateException extends Exception {
    public ExpirationOutOfDateException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws ExpirationOutOfDateException {
        if (e) {
            throw new ExpirationOutOfDateException(message);
        }
    }
}
