package com.simplesdental.desafiojavabackend.config.message;

public interface MessageSourceService {

    String getMessage(String messageProperties);

    String getMessage(String messageProperties, Object... args);
}