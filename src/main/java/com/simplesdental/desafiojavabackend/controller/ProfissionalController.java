package com.simplesdental.desafiojavabackend.controller;

import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.dto.response.ErrorMessage;
import com.simplesdental.desafiojavabackend.dto.response.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProfissionalController {

    @Operation(summary = "Busca todos os profissionais.",
            description = "Retorna uma lista paginada de todos os profissionais cadastrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operação bem sucedida",
                            content = @Content(schema = @Schema(implementation = PagedResponse.class)))
            }
    )
    ResponseEntity<PagedResponse<Map<String, Object>>> getAllProfissionais(@Parameter(description = "Termo para ser pesquisado") @NotBlank String texto,
                                                                           @Parameter(description = "Campos para serem retornados") String[] fields,
                                                                           @ParameterObject Pageable pageable);

    @Operation(summary = "Cria um profissional.",
            description = "Cria um profissional com dados enviados pelo body da requisição.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Profissional criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ProfissionalDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Um ou mais dados do profissional enviados na requisição estão incorretos.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Não foi possível processar a sua solicitação devido a um erro de negócio.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    ResponseEntity<ProfissionalDto> createProfissional(
            @Parameter(description = "Objeto com dados do profissional para criação.") @Valid ProfissionalRequest contatoRequest);

    @Operation(summary = "Atualiza os dados do profissional",
            description = "Atualiza os dados de um profissional pelo id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operação bem sucedida.",
                            content = @Content(schema = @Schema(implementation = ProfissionalDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Um ou mais dados do profissional enviados na requisição estão incorretos.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Profissional não econtrado para o id informado.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))}
    )
    ResponseEntity<ProfissionalDto> updateProfissional(
            @Parameter(description = "Id do profissional.") @Positive Long id,
            @Parameter(description = "Objeto com dados do profissional para atualização.") @Valid ProfissionalRequest contatoRequest);

    @Operation(summary = "Busca um profissional.",
            description = "Busca um profissional pelo id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operação bem sucedida.",
                            content = @Content(schema = @Schema(implementation = ProfissionalDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "O id do profissional precisa ser maior que zero.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Profissional não econtrado para o id informado.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))}
    )
    ResponseEntity<ProfissionalDto> getProfissionalById(@Parameter(description = "Id do profissional.") @Positive Long id);

    @Operation(summary = "Deleta um profissional",
            description = "Deleta profissional pelo id.",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "O id do profissional precisa ser maior que zero.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Profissional foi deletado com sucesso.")
            }
    )
    ResponseEntity<ProfissionalDto> deleteProfissional(
            @Parameter(description = "Id do profissional") @Positive Long id);
}
