package com.simplesdental.desafiojavabackend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContatoRequest {

   @Size(max = 200)
   @NotNull(message = "{validation.contato.nome.notnull}")
   @NotBlank(message = "{validation.contato.nome.notblank}")
   @Pattern(regexp = "^[a-zA-Z0-9._\\- ]+$", message = "{validation.contato.nome.regex}")
   @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do tipo de contato.")
   String nome;

   @Size(max = 25)
   @NotNull(message = "{validation.contato.contato.notnull}")
   @NotBlank(message = "{validation.contato.contato.notblank}")
   @Pattern(regexp = "^[0-9()\\s\\-+]+$", message = "{validation.contato.contato.regex}")
   @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Número de telefone para contato.")
   String contato;

   @NotNull(message = "{validation.profissional.profissionalId.notnull}")
   @Positive(message = "{validation.profissional.profissionalId.positive}")
   @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Identificador do profissional gerado pelo banco de dados. Maior que zero.")
   Long profissionalId;
}
