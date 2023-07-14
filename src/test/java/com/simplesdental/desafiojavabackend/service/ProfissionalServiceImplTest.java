package com.simplesdental.desafiojavabackend.service;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.entity.Profissional;
import com.simplesdental.desafiojavabackend.mapper.ProfissionalMapper;
import com.simplesdental.desafiojavabackend.repository.ProfissionalRepository;
import com.simplesdental.desafiojavabackend.service.impl.ProfissionalServiceImpl;
import com.simplesdental.desafiojavabackend.util.MessageServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfissionalServiceImplTest {

    @InjectMocks
    private ProfissionalServiceImpl profissionalService;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private ProfissionalMapper profissionalMapper;

    @Mock
    private MessageServiceUtil messageServiceUtil;

    @Test
    void getAllProfissionais_WithValidParameters_ReturnsProfissionaisPage() {
        Pageable pageable = Pageable.unpaged();
        String texto = "example";
        String[] fields = new String[]{"nome", "cargo"};
        Page<Map<String, Object>> expectedPage = Page.empty();

        when(profissionalRepository.findByTextoAndFieldsDynamic(texto, fields, pageable)).thenReturn(expectedPage);

        Page<Map<String, Object>> resultPage = profissionalService.getAllProfissionais(pageable, texto, fields);

        assertEquals(expectedPage, resultPage);
    }

    @Test
    void createProfissional_WithValidProfissionalRequest_ReturnsProfissionalDto() {
        ProfissionalRequest profissionalRequest = ProfissionalRequest.builder()
                .build();

        var profissional = new Profissional();
        profissional.setId(1L);

        var profissionalDto = ProfissionalDto.builder()
                .id(1L)
                .build();

        when(profissionalMapper.toProfissional(profissionalRequest)).thenReturn(profissional);
        when(profissionalRepository.save(profissional)).thenReturn(profissional);
        when(profissionalMapper.toProfissionalDto(profissional)).thenReturn(profissionalDto);

        ProfissionalDto resultDto = profissionalService.createProfissional(profissionalRequest);

        assertNotNull(resultDto);
        assertEquals(profissionalDto, resultDto);
    }

    @Test
    void updateProfissional_WithValidIdAndProfissionalRequest_ReturnsUpdatedProfissionalDto() {
        Long profissionalId = 1L;
        ProfissionalRequest profissionalRequest = ProfissionalRequest.builder()
                .build();

        var profissional = new Profissional();
        profissional.setId(profissionalId);

        var profissionalDto = ProfissionalDto.builder()
                .id(profissionalId)
                .build();

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.of(profissional));
        when(profissionalMapper.toProfissionalDto(profissional)).thenReturn(profissionalDto);
        when(profissionalRepository.save(profissional)).thenReturn(profissional);

        ProfissionalDto resultDto = profissionalService.updateProfissional(profissionalId, profissionalRequest);

        assertNotNull(resultDto);
        assertEquals(profissionalDto, resultDto);
    }

    @Test
    void updateProfissional_WithInvalidId_ThrowsEntityNotFoundException() {
        Long profissionalId = 1L;
        ProfissionalRequest profissionalRequest = ProfissionalRequest.builder()
                .build();

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> profissionalService.updateProfissional(profissionalId, profissionalRequest));
    }

    @Test
    void getProfissionalById_WithValidId_ReturnsProfissionalDto() {
        Long profissionalId = 1L;
        var profissional = new Profissional();
        profissional.setId(profissionalId);

        var profissionalDto = ProfissionalDto.builder()
                .id(profissionalId)
                .build();

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.of(profissional));
        when(profissionalMapper.toProfissionalDto(profissional)).thenReturn(profissionalDto);

        ProfissionalDto resultDto = profissionalService.getProfissionalById(profissionalId);

        assertNotNull(resultDto);
        assertEquals(profissionalDto, resultDto);
    }

    @Test
    void getProfissionalById_WithInvalidId_ReturnsNull() {
        Long profissionalId = 1L;

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.empty());

        ProfissionalDto resultDto = profissionalService.getProfissionalById(profissionalId);

        assertNull(resultDto);
    }

    @Test
    void deleteProfissional_WithValidId_ReturnsDeletedProfissionalDto() {
        Long profissionalId = 1L;
        var profissional = new Profissional();
        profissional.setId(profissionalId);

        var profissionalDto = ProfissionalDto.builder()
                .id(profissionalId)
                .build();

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.of(profissional));
        when(profissionalRepository.save(profissional)).thenReturn(profissional);
        when(profissionalMapper.toProfissionalDto(profissional)).thenReturn(profissionalDto);

        ProfissionalDto resultDto = profissionalService.deleteProfissional(profissionalId);

        assertNotNull(resultDto);
        assertEquals(profissionalDto, resultDto);
        assertFalse(profissional.getAtivo());
    }

    @Test
    void deleteProfissional_WithInvalidId_ThrowsEntityNotFoundException() {
        Long profissionalId = 1L;

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> profissionalService.deleteProfissional(profissionalId));
    }
}