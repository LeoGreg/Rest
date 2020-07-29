package am.job.rest.Controller.advice;

import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberContainsLettersException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberOutOfSizeRestrictionError;
import am.job.rest.util.transfer.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class ExceptionToStatusConnector {



    @ResponseBody
    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
//409
    String duplicateD(DuplicateDataException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }

    @ResponseBody()
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//500
    String runTime(RuntimeException ex) {
        log.error(">>>something went wrong:");
        return "something went wrong";
    }

    @ResponseBody
    @ExceptionHandler(AccessNumberOutOfSizeRestrictionError.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
//400
    String accessProblem(AccessNumberOutOfSizeRestrictionError ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }




    @ResponseBody
    @ExceptionHandler(AccessNumberContainsLettersException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
//409
    String accessIssue(AccessNumberContainsLettersException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notF(NotFoundException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongBalanceException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
//409
    String balanceIssue(WrongBalanceException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(ExpirationOutOfDateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
//409
    String expire(ExpirationOutOfDateException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(NotSignedUpCardException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
//409
    String expire(NotSignedUpCardException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NumberSizeException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String expire(NumberSizeException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameNumberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
//409
    String expire(SameNumberException ex) {
        log.warn(">>>" + ex.getMessage() + ":");
        return ex.getMessage();
    }
}
