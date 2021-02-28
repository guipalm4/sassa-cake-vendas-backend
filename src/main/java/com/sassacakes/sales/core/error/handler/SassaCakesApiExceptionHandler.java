package com.sassacakes.sales.core.error.handler;

import com.sassacakes.sales.core.error.ErrorMessage;
import com.sassacakes.sales.core.error.converter.ErrorMessageConverter;
import com.sassacakes.sales.core.exception.BusinessException;
import com.sassacakes.sales.core.exception.FileException;
import com.sassacakes.sales.core.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.File;

@ControllerAdvice
public class SassaCakesApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SassaCakesApiExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(final NotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage> businessException(final BusinessException e) {
        return error(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorMessage> fileException(final FileException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exception(final Exception e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ErrorMessage> error(final Exception exception, final HttpStatus httpStatus) {
        ErrorMessageConverter converter = new ErrorMessageConverter();
        LOGGER.error("Erro ao processar request. Error: {}", exception.getMessage());
        return new ResponseEntity<>(converter.convert(exception), httpStatus);
    }

}