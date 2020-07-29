package am.job.rest.util.employeeValid;


import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberContainsLettersException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberOutOfSizeRestrictionError;

public class AccessNumberValidation {
    public static void validate(String number) throws AccessNumberOutOfSizeRestrictionError {

        AccessNumberOutOfSizeRestrictionError.check(number.length() != 5, "accessNumber.is.not.5");
    }

    public static void checkIfThereAreLetters(String number) throws AccessNumberContainsLettersException {
        String clean = number.replaceAll("\\D+", "");
        AccessNumberContainsLettersException.check(clean.length() != number.length(), "letter.in.number");
    }
}
