package com.simplesdental.desafiojavabackend.service.impl;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.mapper.ProfissionalMapper;
import com.simplesdental.desafiojavabackend.repository.ProfissionalRepository;
import com.simplesdental.desafiojavabackend.service.ProfissionalService;
import com.simplesdental.desafiojavabackend.util.MessageServiceUtil;
import com.simplesdental.desafiojavabackend.validator.PageableValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ProfissionalServiceImpl implements ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalMapper profissionalMapper;
    private final MessageServiceUtil messageServiceUtil;

    @Override
    public Page<Map<String, Object>> getAllProfissionais(Pageable pageable, String texto, String[] fields) {

        PageableValidator.validaPaginaENomeColunasOrdenacaoDoPageable(pageable);
        Page<Map<String, Object>> profissionaisPage;

        profissionaisPage = profissionalRepository.findByTextoAndFieldsDynamic(texto, fields, pageable);
        return profissionaisPage;
    }

    @Override
    public ProfissionalDto createProfissional(ProfissionalRequest profissionalRequest) {

        var profissional = profissionalMapper.toProfissional(profissionalRequest);
        profissional.setCreatedDate(LocalDate.now());
        profissional.setAtivo(true);

        return profissionalMapper.toProfissionalDto(profissionalRepository.save(profissional));
    }

    @Override
    public ProfissionalDto updateProfissional(Long id, ProfissionalRequest profissionalRequest) {
        var profissional = profissionalRepository.findById(id).orElse(null);
        if (isNull(profissional)) {
            throw new EntityNotFoundException(messageServiceUtil.getMessageForProfessionalNotFoundById(id));
        }
        profissionalMapper.updateProfissionalFromProfissionalRequest(profissionalRequest, profissional);
        return profissionalMapper.toProfissionalDto(profissionalRepository.save(profissional));
    }

    @Override
    public ProfissionalDto getProfissionalById(Long id) {
        var profissional = profissionalRepository.findById(id).orElse(null);
        return profissionalMapper.toProfissionalDto(profissional);
    }

    @Override
    public ProfissionalDto deleteProfissional(Long id) {
        var profissional = profissionalRepository.findById(id).orElse(null);
        if (isNull(profissional)) {
            throw new EntityNotFoundException(messageServiceUtil.getMessageForProfessionalNotFoundById(id));
        }
        profissional.setAtivo(false);
        return profissionalMapper.toProfissionalDto(profissionalRepository.save(profissional));
    }
}