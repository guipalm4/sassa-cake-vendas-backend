package com.sassacakes.sales.error.exception;

public class NotFoundException extends SassaCakesException {

    public NotFoundException() {
        this("Not Found");
    }

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String message, final String code) {
        super(message, code);
    }

    public NotFoundException(final String message, final ExceptionCode code) {
        super(message, code);
    }

    public NotFoundException(final Throwable cause) {
        super(cause);
    }

    public NotFoundException(final Throwable cause, final String code) {
        super(cause, code);
    }

    public NotFoundException(final Throwable cause, final ExceptionCode code) {
        super(cause, code);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(final String message, final Throwable cause, final String code) {
        super(message, cause, code);
    }

    public NotFoundException(final String message, final Throwable cause, final ExceptionCode code) {
        super(message, cause, code);
    }
}
