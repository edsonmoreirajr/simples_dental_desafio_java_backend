package com.simplesdental.desafiojavabackend.util;

import com.simplesdental.desafiojavabackend.config.message.MessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceUtil {

    public static final String ENTITY_NOT_FOUND_PROFISSIONAL = "entity-not-found.profissional";
    public static final String ENTITY_NOT_FOUND_CONTATO = "entity-not-found.contato";
    private final MessageSourceService messageSourceService;
    @Autowired
    public MessageServiceUtil(MessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    public String getMessageForProfessionalNotFoundById(Long id) {
        return messageSourceService.getMessage(ENTITY_NOT_FOUND_PROFISSIONAL, id);
    }

    public String getMessageForContatoNotFoundById(Long id) {
        return messageSourceService.getMessage(ENTITY_NOT_FOUND_CONTATO, id);
    }
}
