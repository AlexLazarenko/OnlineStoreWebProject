package edu.epam.web.exception;

public class EmailException extends Exception {
    public EmailException() {
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EmailException(Throwable throwable) {
        super(throwable);
    }
}
