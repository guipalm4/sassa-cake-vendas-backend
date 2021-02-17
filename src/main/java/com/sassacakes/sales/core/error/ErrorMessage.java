package com.sassacakes.sales.core.error;

import java.util.Optional;

public class ErrorMessage {

    private final Optional<String> code;

    private final String msg;

    public ErrorMessage() {
        this.code = Optional.empty();
        this.msg = null;
    }
    public ErrorMessage(final Optional<String> code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ErrorMessage(final String code, final String message) {
        this(Optional.ofNullable(code), message);
    }


    public Optional<String> getCode() {
        return code;
    }

    public ErrorMessage(final String msg) {
        this(Optional.empty(), msg);
    }

    public String getMsg() {
        return msg;
    }
}

