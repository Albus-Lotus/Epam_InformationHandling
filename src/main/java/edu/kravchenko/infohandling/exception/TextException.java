package edu.kravchenko.infohandling.exception;

public class TextException extends Exception {

    public TextException() {
    }

    public TextException(String message) {
        super(message);
    }

    public TextException(Throwable cause) {
        super(cause);
    }

    public TextException(String message, Throwable cause) {
        super(message, cause);
    }
}
