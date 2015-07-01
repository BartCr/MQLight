package be.cremers.mqlight.core.exception;

public class MQLightException extends Exception {

    public MQLightException() {
    }

    public MQLightException(String message) {
        super(message);
    }

    public MQLightException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQLightException(Throwable cause) {
        super(cause);
    }

    public MQLightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
