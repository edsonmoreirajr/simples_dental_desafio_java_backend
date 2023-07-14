package com.simplesdental.desafiojavabackend.dto;

import com.simplesdental.desafiojavabackend.entity.enums.ProfissionaisCargos;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProfissionalDto {

    Long id;
    String nome;
    ProfissionaisCargos cargo;
    LocalDate nascimento;
    LocalDate createdDate;
    Boolean ativo;
}