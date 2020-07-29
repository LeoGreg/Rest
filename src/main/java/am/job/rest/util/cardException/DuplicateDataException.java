package am.job.rest.util.cardException;

public class DuplicateDataException extends Exception {

    public DuplicateDataException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws DuplicateDataException {
        if (ex) {
            throw new DuplicateDataException(message);
        }
    }
}
