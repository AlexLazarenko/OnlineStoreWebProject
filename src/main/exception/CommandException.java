package edu.epam.web.exception;

public class CommandException extends Exception {

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CommandException(Throwable throwable) {
        super(throwable);
    }
}


