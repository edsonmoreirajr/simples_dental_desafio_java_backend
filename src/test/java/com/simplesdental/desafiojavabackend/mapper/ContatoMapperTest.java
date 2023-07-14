package com.simplesdental.desafiojavabackend.mapper;

import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import com.simplesdental.desafiojavabackend.entity.Contato;
import com.simplesdental.desafiojavabackend.entity.Profissional;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContatoMapperTest {

    private final ContatoMapper contatoMapper = Mappers.getMapper(ContatoMapper.class);

    @Test
    public void testToContatoDto() {
        var contato = Contato.builder()
                .id(1L)
                .nome("casa")
                .contato("1234567890")
                .createdDate(LocalDate.now())
                .profissional(Profissional.builder().id(1L).build())
                .build();

        var contatoDto = contatoMapper.toContatoDto(contato);

        assertEquals(contato.getId(), contatoDto.getId());
        assertEquals(contato.getNome(), contatoDto.getNome());
        assertEquals(contato.getContato(), contatoDto.getContato());
        assertEquals(contato.getCreatedDate(), contatoDto.getCreatedDate());
        assertEquals(contato.getProfissional().getId(), contatoDto.getProfissionalId());
    }

    @Test
    public void testToContato() {
        var contatoRequest = ContatoRequest.builder()
                .nome("casa")
                .contato("1234567890")
                .profissionalId(1L)
                .build();

        var contato = contatoMapper.toContato(contatoRequest);

        assertEquals(contatoRequest.getNome(), contato.getNome());
        assertEquals(contatoRequest.getContato(), contato.getContato());
        assertEquals(contatoRequest.getProfissionalId(), contato.getProfissional().getId());
    }

    @Test
    public void testUpdateContatoFromContatoRequest() {
        var contatoRequest = ContatoRequest.builder()
                .nome("casa")
                .contato("1234567890")
                .profissionalId(1L)
                .build();

        var contato = Contato.builder()
                .id(1L)
                .nome("celular")
                .contato("0987654321")
                .createdDate(LocalDate.now())
                .profissional(Profissional.builder().id(2L).build())
                .build();

        contatoMapper.updateContatoFromContatoRequest(contatoRequest, contato);

        assertEquals(contatoRequest.getNome(), contato.getNome());
        assertEquals(contatoRequest.getContato(), contato.getContato());
        assertEquals(contatoRequest.getProfissionalId(), contato.getProfissional().getId());
    }
}