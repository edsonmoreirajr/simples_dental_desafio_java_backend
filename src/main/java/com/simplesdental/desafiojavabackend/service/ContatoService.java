package com.simplesdental.desafiojavabackend.service;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoService {

    Page<ContatoDto> getAllContatos(Pageable pageable, String termoPesquisado);

    ContatoDto createContato(ContatoRequest contatoRequest);

    ContatoDto updateContato(Long id, ContatoRequest contatoRequest);

    ContatoDto getContatoById(Long id);

    void deleteContato(Long id);
}
