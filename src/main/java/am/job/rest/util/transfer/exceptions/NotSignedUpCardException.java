package am.job.rest.util.transfer.exceptions;

public class NotSignedUpCardException extends Exception {
    public NotSignedUpCardException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws NotSignedUpCardException {
        if (e) {
            throw new NotSignedUpCardException(message);
        }
    }
}
