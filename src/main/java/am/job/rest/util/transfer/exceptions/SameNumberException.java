package am.job.rest.util.transfer.exceptions;

public class SameNumberException extends Exception {
    public SameNumberException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws SameNumberException {
        if (e) {
            throw new SameNumberException(message);
        }
    }
}