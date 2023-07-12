package com.simplesdental.desafiojavabackend.service;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfissionalServie {

    Page<ProfissionalDto> getAllProfissionals(Pageable pageable, String termoPesquisado);

    ProfissionalDto createProfissional(ProfissionalRequest profissionalRequest);

    ProfissionalDto updateProfissional(Long id, ProfissionalRequest profissionalRequest);

    ProfissionalDto getProfissionalById(Long id);

    void deleteProfissional(Long id);
}