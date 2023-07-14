package com.simplesdental.desafiojavabackend.service;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import com.simplesdental.desafiojavabackend.entity.Contato;
import com.simplesdental.desafiojavabackend.entity.Profissional;
import com.simplesdental.desafiojavabackend.mapper.ContatoMapper;
import com.simplesdental.desafiojavabackend.repository.ContatoRepository;
import com.simplesdental.desafiojavabackend.repository.ProfissionalRepository;
import com.simplesdental.desafiojavabackend.service.impl.ContatoServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContatoServiceImplTest {

    @InjectMocks
    private ContatoServiceImpl contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private ContatoMapper contatoMapper;

    @Mock
    private MessageServiceUtil messageServiceUtil;

    @Test
    void getAllContatos_WithValidParameters_ReturnsContatosPage() {
        Pageable pageable = Pageable.unpaged();
        String texto = "example";
        String[] fields = new String[]{"nome", "contato"};
        Page<Map<String, Object>> expectedPage = Page.empty();

        when(contatoRepository.findByTextoAndFieldsDynamic(texto, fields, pageable)).thenReturn(expectedPage);

        Page<Map<String, Object>> resultPage = contatoService.getAllContatos(pageable, texto, fields);

        assertEquals(expectedPage, resultPage);
    }

    @Test
    void createContato_WithValidContatoRequest_ReturnsContatoDto() {
        Long profissionalId = 1L;
        ContatoRequest contatoRequest = ContatoRequest.builder()
                .profissionalId(profissionalId)
                .build();

        var profissional = new Profissional();
        profissional.setId(profissionalId);

        var contato = new Contato();
        contato.setId(1L);

        var contatoDto = ContatoDto.builder()
                .id(1L)
                .build();

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.of(profissional));
        when(contatoMapper.toContato(contatoRequest)).thenReturn(contato);
        when(contatoRepository.save(contato)).thenReturn(contato);
        when(contatoMapper.toContatoDto(contato)).thenReturn(contatoDto);

        ContatoDto resultDto = contatoService.createContato(contatoRequest);

        assertNotNull(resultDto);
        assertEquals(contatoDto, resultDto);
    }

    @Test
    void createContato_WithInvalidProfissionalId_ThrowsEntityNotFoundException() {
        Long profissionalId = 1L;
        ContatoRequest contatoRequest = ContatoRequest.builder()
                .profissionalId(profissionalId)
                .build();

        when(profissionalRepository.findById(profissionalId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> contatoService.createContato(contatoRequest));
    }

    @Test
    void updateContato_WithValidIdAndContatoRequest_ReturnsUpdatedContatoDto() {
        Long contatoId = 1L;
        ContatoRequest contatoRequest = ContatoRequest.builder()
                .build();

        var contato = new Contato();
        contato.setId(contatoId);

        var contatoDto = ContatoDto.builder()
                .id(contatoId)
                .build();

        when(contatoRepository.findById(contatoId)).thenReturn(Optional.of(contato));
        when(contatoMapper.toContatoDto(contato)).thenReturn(contatoDto);
        when(contatoRepository.save(contato)).thenReturn(contato);

        ContatoDto resultDto = contatoService.updateContato(contatoId, contatoRequest);

        assertNotNull(resultDto);
        assertEquals(contatoDto, resultDto);
    }

    @Test
    void updateContato_WithInvalidId_ThrowsEntityNotFoundException() {
        Long contatoId = 1L;
        ContatoRequest contatoRequest = ContatoRequest.builder()
                .build();

        when(contatoRepository.findById(contatoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> contatoService.updateContato(contatoId, contatoRequest));
    }

    @Test
    void getContatoById_WithValidId_ReturnsContatoDto() {
        Long contatoId = 1L;
        var contato = new Contato();
        contato.setId(contatoId);

        var contatoDto = ContatoDto.builder()
                .id(contatoId)
                .build();

        when(contatoRepository.findById(contatoId)).thenReturn(Optional.of(contato));
        when(contatoMapper.toContatoDto(contato)).thenReturn(contatoDto);

        ContatoDto resultDto = contatoService.getContatoById(contatoId);

        assertNotNull(resultDto);
        assertEquals(contatoDto, resultDto);
    }

    @Test
    void getContatoById_WithInvalidId_ReturnsNull() {
        Long contatoId = 1L;

        when(contatoRepository.findById(contatoId)).thenReturn(Optional.empty());

        ContatoDto resultDto = contatoService.getContatoById(contatoId);

        assertNull(resultDto);
    }

    @Test
    void deleteContato_WithValidId_DeletesContato() {
        Long contatoId = 1L;

        assertDoesNotThrow(() -> contatoService.deleteContato(contatoId));
    }
}