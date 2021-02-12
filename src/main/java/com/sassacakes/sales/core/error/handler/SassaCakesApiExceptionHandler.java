package com.sassacakes.sales.core.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sassacakes.sales.core.error.ErrorMessage;
import com.sassacakes.sales.core.error.converter.ErrorMessageConverter;
import com.sassacakes.sales.core.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class SassaCakesApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(final NotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exception(final Exception e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> error(final Exception exception, final HttpStatus httpStatus) {
        ErrorMessageConverter converter = new ErrorMessageConverter();
        log.error("Erro ao processar request. Error: {}", exception.getMessage());
        return new ResponseEntity<>(converter.convert(exception), httpStatus);
    }

}