package com.simplesdental.desafiojavabackend.service.impl;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import com.simplesdental.desafiojavabackend.mapper.ContatoMapper;
import com.simplesdental.desafiojavabackend.repository.ContatoRepository;
import com.simplesdental.desafiojavabackend.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;
    @Override
    public Page<ContatoDto> getAllContatos(Pageable pageable, String termoPesquisado) {
        return null;
    }

    @Override
    public ContatoDto createContato(ContatoRequest contatoRequest) {
        return null;
    }

    @Override
    public ContatoDto updateContato(Long id, ContatoRequest contatoRequest) {
        return null;
    }

    @Override
    public ContatoDto getContatoById(Long id) {
        return null;
    }

    @Override
    public void deleteContato(Long id) {

    }
}