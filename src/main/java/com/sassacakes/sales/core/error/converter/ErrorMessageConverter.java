package com.sassacakes.sales.core.error.converter;

import com.sassacakes.sales.core.error.ErrorMessage;
import com.sassacakes.sales.core.exception.SassaCakesException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class ErrorMessageConverter {

    public ErrorMessage convert(final Throwable throwable) {
        final Throwable t = this.getRootCause(throwable);

        if (t instanceof SassaCakesException) return this.toErrorMessage((SassaCakesException) t);
        else return this.toErrorMessage(t);
    }

    private ErrorMessage toErrorMessage(final SassaCakesException sassaCakesException) {
        return new ErrorMessage(sassaCakesException.getCode(), this.getRootMessage(sassaCakesException));
    }

    private ErrorMessage toErrorMessage(final Throwable throwable) {
        return new ErrorMessage(this.getRootMessage(throwable));
    }

    private <T extends Throwable> T getRootCause(final T throwable) {
        T cause = throwable;

        while(cause.getCause() != null) cause = (T) cause.getCause();

        return cause;
    }

    private String getRootMessage(final Throwable throwable) {
        return Optional.ofNullable(throwable.getMessage())
                .orElseGet(() -> this.getMessageFromStack(throwable));
    }

    private String getMessageFromStack(final Throwable throwable) {
        final StackTraceElement[] stackTraces = throwable.getStackTrace();

        final String exceptionClassName = throwable.getClass().getName();

        return stackTraces.length < 1 ? exceptionClassName :
                Arrays.stream(new StackTraceElement[]{ stackTraces[0] })
                        .map(trace -> exceptionClassName + ": " + trace.getClassName() + "." + trace.getMethodName() + ":" + trace.getLineNumber())
                        .collect(Collectors.joining());
    }
}
