package edu.epam.web.exception;

public class ValidatorException extends Exception {
    public ValidatorException() {
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ValidatorException(Throwable throwable) {
        super(throwable);
    }
}