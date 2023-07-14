package com.simplesdental.desafiojavabackend.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ContatoDto {

    Long id;
    String nome;
    String contato;
    LocalDate createdDate;
    Long profissionalId;
}
