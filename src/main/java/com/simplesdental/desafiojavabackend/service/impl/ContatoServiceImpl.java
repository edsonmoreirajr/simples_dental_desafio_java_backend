package com.simplesdental.desafiojavabackend.service.impl;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import com.simplesdental.desafiojavabackend.mapper.ContatoMapper;
import com.simplesdental.desafiojavabackend.repository.ContatoRepository;
import com.simplesdental.desafiojavabackend.repository.ProfissionalRepository;
import com.simplesdental.desafiojavabackend.service.ContatoService;
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
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ContatoMapper contatoMapper;
    private final MessageServiceUtil messageServiceUtil;

    @Override
    public Page<Map<String, Object>> getAllContatos(Pageable pageable, String texto, String[] fields) {
        PageableValidator.validaPaginaENomeColunasOrdenacaoDoPageable(pageable);
        Page<Map<String, Object>> contatosPage;

        contatosPage = contatoRepository.findByTextoAndFieldsDynamic(texto, fields, pageable);
        return contatosPage;
    }

    @Override
    public ContatoDto createContato(ContatoRequest contatoRequest) {

        var profissional = profissionalRepository.findById(contatoRequest.getProfissionalId()).orElse(null);

        if (isNull(profissional)) {
            throw new EntityNotFoundException(messageServiceUtil.getMessageForProfessionalNotFoundById(contatoRequest.getProfissionalId()));
        }

        var contato = contatoMapper.toContato(contatoRequest);
        contato.setCreatedDate(LocalDate.now());
        return contatoMapper.toContatoDto(contatoRepository.save(contato));
    }

    @Override
    public ContatoDto updateContato(Long id, ContatoRequest contatoRequest) {
        var contato = contatoRepository.findById(id).orElse(null);
        if (isNull(contato)) {
            throw new EntityNotFoundException(messageServiceUtil.getMessageForContatoNotFoundById(id));
        }
        contatoMapper.updateContatoFromContatoRequest(contatoRequest, contato);
        return contatoMapper.toContatoDto(contatoRepository.save(contato));
    }

    @Override
    public ContatoDto getContatoById(Long id) {
        var contato = contatoRepository.findById(id).orElse(null);
        return contatoMapper.toContatoDto(contato);
    }

    @Override
    public void deleteContato(Long id) {
        contatoRepository.deleteById(id);
    }
}