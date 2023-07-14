package com.simplesdental.desafiojavabackend.service;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ProfissionalService {

    Page<Map<String, Object>> getAllProfissionais(Pageable pageable, String texto, String[] fields);

    ProfissionalDto createProfissional(ProfissionalRequest profissionalRequest);

    ProfissionalDto updateProfissional(Long id, ProfissionalRequest profissionalRequest);

    ProfissionalDto getProfissionalById(Long id);

    ProfissionalDto deleteProfissional(Long id);
}