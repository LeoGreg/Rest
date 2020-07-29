package am.job.rest.util.transfer.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws NotFoundException {
        if (e) {
            throw new NotFoundException(message);
        }
    }
}
