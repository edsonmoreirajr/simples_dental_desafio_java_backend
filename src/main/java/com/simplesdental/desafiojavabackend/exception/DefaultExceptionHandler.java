package com.simplesdental.desafiojavabackend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.simplesdental.desafiojavabackend.dto.response.ErrorMessage;
import com.simplesdental.desafiojavabackend.dto.response.ErrorMessages;
import com.simplesdental.desafiojavabackend.exception.entities.BusinessException;
import com.simplesdental.desafiojavabackend.exception.entities.InvalidArgumentRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.simplesdental.desafiojavabackend.util.LoggerUtil.logErrorDefault;
import static com.simplesdental.desafiojavabackend.util.LoggerUtil.logErrorForEnum;
import static com.simplesdental.desafiojavabackend.util.LoggerUtil.logErrorForMultipleArguments;


@ControllerAdvice
@Slf4j
@ResponseBody
public class DefaultExceptionHandler {

    private static final Pattern ENUM_VALUES = Pattern.compile("\\[.+\\]");

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleBaseException(Exception ex, WebRequest request) {
        var errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));
        logErrorDefault(ex);
        return errorMessage;
    }


    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage handleBusinessException(RuntimeException ex, WebRequest request) {
        logErrorDefault(ex);
        return new ErrorMessage(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleAsNotFound(RuntimeException ex, WebRequest request) {
        logErrorDefault(ex);
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            InvalidArgumentRequestException.class,
            jakarta.validation.ConstraintViolationException.class,
            org.hibernate.exception.ConstraintViolationException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAsBadRequest(RuntimeException ex, WebRequest request) {
        logErrorDefault(ex);
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessages handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        var messages = new ArrayList<String>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            messages.add(error.getDefaultMessage());
        }
        logErrorForMultipleArguments(ex, messages);
        return new ErrorMessages(HttpStatus.BAD_REQUEST.value(), messages, request.getDescription(false));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessage handleJsonErrors(HttpMessageNotReadableException ex, WebRequest request) {
        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            var matchEnumValues = ENUM_VALUES.matcher(invalidFormatException.getMessage());

            if (matchEnumValues.find()) {
                String campo = invalidFormatException.getPath().get(0).getFieldName();
                String message = "Valor inválido para o campo: " + campo + ". Os valores possíveis são: " + matchEnumValues.group(0);

                logErrorForEnum(ex, message);
                return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), message, request.getDescription(false));
            }
        }

        logErrorDefault(ex);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
    }
}