package com.simplesdental.desafiojavabackend.mapper;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.entity.Profissional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING)
public interface ProfissionalMapper {

    ProfissionalDto toProfissionalDto(Profissional profissional);

    Profissional toProfissional(ProfissionalRequest profissionalRequest);

    void updateProfissionalFromProfissionalRequest(ProfissionalRequest profissionalRequest, @MappingTarget Profissional profissional);
}