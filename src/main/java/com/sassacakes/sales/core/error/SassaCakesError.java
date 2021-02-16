package com.sassacakes.sales.core.error;

import java.util.function.BiFunction;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.support.MessageSourceAccessor;

import com.sassacakes.sales.core.exception.ExceptionCode;
import com.sassacakes.sales.core.exception.NotFoundException;
import com.sassacakes.sales.core.exception.SassaCakesException;

public enum SassaCakesError implements ExceptionCode {

    PRODUCT_NOT_FOUND("SALES-0001", "product.not.found"),
    CATEGORY_NOT_FOUND("SALES-0002", "category.not.found"),
    CUSTOMER_NOT_FOUND("SALES-0003", "customer.not.found"),
    CUSTOMER_LIMIT_EXCEEDED("SALES-0004", "customer.limit.exceeded");

    private final String code;
    private final String message;

    SassaCakesError(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public NotFoundException asNotFoundException(MessageSourceAccessor message, Object... args) {
        return exception(NotFoundException::new, message, args);
    }

    private <T extends SassaCakesException> T exception(BiFunction<String, String, T> error,
                                                        MessageSourceAccessor messageSource, Object... args) {
        if (ArrayUtils.isEmpty(args)) {
            return error.apply(messageSource.getMessage(message), code);
        }
        return error.apply(messageSource.getMessage(message, args), code);
    }



}
