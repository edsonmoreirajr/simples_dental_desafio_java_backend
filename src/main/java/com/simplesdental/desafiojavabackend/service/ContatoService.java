package com.simplesdental.desafiojavabackend.service;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ContatoService {

    Page<Map<String, Object>> getAllContatos(Pageable pageable, String texto, String[] fields);

    ContatoDto createContato(ContatoRequest contatoRequest);

    ContatoDto updateContato(Long id, ContatoRequest contatoRequest);

    ContatoDto getContatoById(Long id);

    void deleteContato(Long id);
}
