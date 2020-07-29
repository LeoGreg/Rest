package am.job.rest.util.employeeValid.employeeExceptions;

public class AccessNumberContainsLettersException extends Exception {
    public AccessNumberContainsLettersException(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws AccessNumberContainsLettersException {
        if (e) {
            throw new AccessNumberContainsLettersException(message);
        }
    }
}
