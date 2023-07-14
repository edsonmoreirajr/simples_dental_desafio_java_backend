package com.simplesdental.desafiojavabackend.mapper;

import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.entity.Profissional;
import com.simplesdental.desafiojavabackend.entity.enums.ProfissionaisCargos;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfissionalMapperTest {

    private final ProfissionalMapper profissionalMapper = Mappers.getMapper(ProfissionalMapper.class);

    @Test
    public void testToProfissionalDto() {
        var profissional = Profissional.builder()
                .id(1L)
                .nome("João Silva")
                .cargo(ProfissionaisCargos.Desenvolvedor)
                .nascimento(LocalDate.of(1990, 6, 15))
                .createdDate(LocalDate.now())
                .ativo(true)
                .build();

        var profissionalDto = profissionalMapper.toProfissionalDto(profissional);

        assertEquals(profissional.getId(), profissionalDto.getId());
        assertEquals(profissional.getNome(), profissionalDto.getNome());
        assertEquals(profissional.getCargo(), profissionalDto.getCargo());
        assertEquals(profissional.getNascimento(), profissionalDto.getNascimento());
        assertEquals(profissional.getCreatedDate(), profissionalDto.getCreatedDate());
        assertEquals(profissional.getAtivo(), profissionalDto.getAtivo());
    }

    @Test
    public void testToProfissional() {
        var profissionalRequest = ProfissionalRequest.builder()
                .nome("Maria Santos")
                .cargo(ProfissionaisCargos.Suporte)
                .nascimento(LocalDate.of(1985, 9, 3))
                .build();

        var profissional = profissionalMapper.toProfissional(profissionalRequest);

        assertEquals(profissionalRequest.getNome(), profissional.getNome());
        assertEquals(profissionalRequest.getCargo(), profissional.getCargo());
        assertEquals(profissionalRequest.getNascimento(), profissional.getNascimento());
    }

    @Test
    public void testUpdateProfissionalFromProfissionalRequest() {
        var profissionalRequest = ProfissionalRequest.builder()
                .nome("José Silva")
                .cargo(ProfissionaisCargos.Tester)
                .nascimento(LocalDate.of(1992, 4, 20))
                .build();

        var profissional = Profissional.builder()
                .id(1L)
                .nome("Carlos Oliveira")
                .cargo(ProfissionaisCargos.Designer)
                .nascimento(LocalDate.of(1990, 5, 15))
                .createdDate(LocalDate.now())
                .ativo(true)
                .build();

        profissionalMapper.updateProfissionalFromProfissionalRequest(profissionalRequest, profissional);

        assertEquals(profissionalRequest.getNome(), profissional.getNome());
        assertEquals(profissionalRequest.getCargo(), profissional.getCargo());
        assertEquals(profissionalRequest.getNascimento(), profissional.getNascimento());
    }
}
