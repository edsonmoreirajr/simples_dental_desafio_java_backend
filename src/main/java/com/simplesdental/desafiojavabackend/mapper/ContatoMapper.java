package com.simplesdental.desafiojavabackend.mapper;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import com.simplesdental.desafiojavabackend.entity.Contato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING)
public interface ContatoMapper {

    @Mapping(target = "profissionalId", source = "profissional.id")
    ContatoDto toContatoDto(Contato contato);

    @Mapping(target = "profissional.id", source = "profissionalId")
    Contato toContato(ContatoRequest contatoRequest);

    @Mapping(target = "profissional.id", source = "profissionalId")
    void updateContatoFromContatoRequest(ContatoRequest contatoRequest, @MappingTarget Contato contato);
}
