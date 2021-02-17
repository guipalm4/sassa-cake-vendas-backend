package com.sassacakes.sales.core.exception;

public class BusinessException extends SassaCakesException {

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final String message, final String code) {
        super(message, code);
    }

    public BusinessException(final String message, final ExceptionCode code) {
        super(message, code);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }

    public BusinessException(final Throwable cause, final String code) {
        super(cause, code);
    }

    public BusinessException(final Throwable cause, final ExceptionCode code) {
        super(cause, code);
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BusinessException(final String message, final Throwable cause, final String code) {
        super(message, cause, code);
    }

    public BusinessException(final String message, final Throwable cause, final ExceptionCode code) {
        super(message, cause, code);
    }
}
