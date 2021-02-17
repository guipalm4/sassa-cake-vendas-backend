package com.sassacakes.sales.core.exception;

import java.util.Optional;

public class SassaCakesException extends RuntimeException {

    private Optional<String> code;

    public SassaCakesException() {
        this("Internal Server Error");
    }

    public SassaCakesException(final String message) {
        super(message);
        this.code = Optional.empty();
    }

    public SassaCakesException(final String message, final String code) {
        super(message);
        this.code = Optional.ofNullable(code);
    }

    public SassaCakesException(final String message, final ExceptionCode code) {
        this(message, code.getCode());
    }

    public SassaCakesException(final Throwable cause) {
        super(cause);
        this.code = Optional.empty();
    }

    public SassaCakesException(final Throwable cause, final String code) {
        super(cause);
        this.code = Optional.ofNullable(code);
    }

    public SassaCakesException(final Throwable cause, final ExceptionCode code) {
        this(cause, code.getCode());
    }

    public SassaCakesException(final String message, final Throwable cause) {
        super(message, cause);
        this.code = Optional.empty();
    }

    public SassaCakesException(final String message, final Throwable cause, final String code) {
        super(message, cause);
        this.code = Optional.ofNullable(code);
    }

    public SassaCakesException(final String message, final Throwable cause, final ExceptionCode code) {
        this(message, cause, code.getCode());
    }

    public Optional<String> getCode() {
        return code;
    }
}
