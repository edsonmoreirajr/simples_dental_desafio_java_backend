package com.simplesdental.desafiojavabackend.dto.request;

import com.simplesdental.desafiojavabackend.entity.enums.ProfissionaisCargos;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProfissionalRequest {

    @Size(max = 200)
    @NotNull(message = "{validation.profissional.nome.notnull}")
    @NotBlank(message = "{validation.profissional.nome.notblank}")
    @Pattern(regexp = "^[a-zA-Z0-9.+_\\- ]+$", message = "{validation.profissional.nome.regex}")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do profissional.")
    String nome;

    @NotNull(message = "{validation.profissional.cargo.notnull}")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Cargo exercido pelo profissional.")
    ProfissionaisCargos cargo;

    @NotNull(message = "{validation.profissional.nascimento.notnull}")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Data de nascimento do profissional.")
    LocalDate nascimento;
}