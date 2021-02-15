package edu.epam.web.exception;

public class PropertyReaderException extends Exception{
    public PropertyReaderException() {
    }

    public PropertyReaderException(String message) {
        super(message);
    }

    public PropertyReaderException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PropertyReaderException(Throwable throwable) {
        super(throwable);
    }
}
