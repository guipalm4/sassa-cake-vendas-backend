package com.sassacakes.sales.core.exception;

public class FileException extends SassaCakesException {

    public FileException(final String message) {
        super(message);
    }

    public FileException(final String message, final String code) {
        super(message, code);
    }


}
