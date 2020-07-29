package am.job.rest.util.transfer.exceptions;

public class NumberSizeException extends Exception {
    public NumberSizeException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws NumberSizeException {
        if (e) {
            throw new NumberSizeException(message);
        }
    }
}
