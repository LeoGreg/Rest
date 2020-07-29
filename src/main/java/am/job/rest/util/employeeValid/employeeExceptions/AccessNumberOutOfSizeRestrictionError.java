package am.job.rest.util.employeeValid.employeeExceptions;


public class AccessNumberOutOfSizeRestrictionError extends Exception {
    public AccessNumberOutOfSizeRestrictionError(String message) {
        super(message);
    }

    public static void check(boolean e, String message) throws AccessNumberOutOfSizeRestrictionError {
        if (e) {
            throw new AccessNumberOutOfSizeRestrictionError(message);
        }
    }
}
