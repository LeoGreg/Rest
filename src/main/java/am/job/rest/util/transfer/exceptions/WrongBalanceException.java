package am.job.rest.util.transfer.exceptions;

public class WrongBalanceException extends Exception {
    public WrongBalanceException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws WrongBalanceException {
        if (e) {
            throw new WrongBalanceException(message);
        }
    }
}
