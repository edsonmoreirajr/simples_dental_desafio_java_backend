package com.simplesdental.desafiojavabackend.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public class LoggerUtil {

    public static void logErrorForEnum(HttpMessageNotReadableException ex, String message) {
        log.error(ex.getClass().getCanonicalName() + ": " + ex.getMessage() + " " + message);
    }

    public static void logErrorForMultipleArguments(MethodArgumentNotValidException ex, List<String> messages) {
        log.error(ex.getClass().getCanonicalName() + ": " + ex.getMessage() + " [" + messages + "]");
    }

    public static void logErrorDefault(Exception ex) {
        log.error(ex.getClass().getCanonicalName() + ": " + ex.getMessage());
    }
}
