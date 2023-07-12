package com.simplesdental.desafiojavabackend.service.impl;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.mapper.ProfissionalMapper;
import com.simplesdental.desafiojavabackend.repository.ProfissionalRepository;
import com.simplesdental.desafiojavabackend.service.ProfissionalServie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalServiceImpl implements ProfissionalServie {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalMapper profissionalMapper;

    @Override
    public Page<ProfissionalDto> getAllProfissionals(Pageable pageable, String termoPesquisado) {
        return null;
    }

    @Override
    public ProfissionalDto createProfissional(ProfissionalRequest profissionalRequest) {
        return null;
    }

    @Override
    public ProfissionalDto updateProfissional(Long id, ProfissionalRequest profissionalRequest) {
        return null;
    }

    @Override
    public ProfissionalDto getProfissionalById(Long id) {
        return null;
    }

    @Override
    public void deleteProfissional(Long id) {

    }
}