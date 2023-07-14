package com.simplesdental.desafiojavabackend.controller;

import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
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

public interface ContatoController {

    @Operation(summary = "Busca todos os contatos.",
            description = "Retorna uma lista paginada de todos os contatos cadastrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operação bem sucedida",
                            content = @Content(schema = @Schema(implementation = PagedResponse.class)))
            }
    )
    ResponseEntity<PagedResponse<Map<String, Object>>> getAllContatos(
            @Parameter(description = "Termo para ser pesquisado") @NotBlank String texto,
            @Parameter(description = "Campos para serem retornados") String[] fields,
            @ParameterObject Pageable pageable);

    @Operation(summary = "Cria um contato.",
            description = "Cria um contato com dados enviados pelo body da requisição.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Contato criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ContatoDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Um ou mais dados do contato enviados na requisição estão incorretos.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Não foi possível processar a sua solicitação devido a um erro de negócio.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    ResponseEntity<ContatoDto> createContato(
            @Parameter(description = "Objeto com dados do contato para criação.") @Valid ContatoRequest contatoRequest);

    @Operation(summary = "Atualiza os dados do contato",
            description = "Atualiza os dados de um contato pelo id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operação bem sucedida.",
                            content = @Content(schema = @Schema(implementation = ContatoDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Um ou mais dados do contato enviados na requisição estão incorretos.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Contato não econtrado para o id informado.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))}
    )
    ResponseEntity<ContatoDto> updateContato(
            @Parameter(description = "Id do contato.") @Positive Long id,
            @Parameter(description = "Objeto com dados do contato para atualização.") @Valid ContatoRequest contatoRequest);

    @Operation(summary = "Busca um contato.",
            description = "Busca um contato pelo id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operação bem sucedida.",
                            content = @Content(schema = @Schema(implementation = ContatoDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "O id do contato precisa ser maior que zero.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Contato não econtrado para o id informado.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))}
    )
    ResponseEntity<ContatoDto> getContatoById(@Parameter(description = "Id do contato.") @Positive Long id);

    @Operation(summary = "Deleta um contato",
            description = "Deleta contato pelo id.",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "O id do contato precisa ser maior que zero.",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Contato foi deletado com sucesso.")
            }
    )
    ResponseEntity<Void> deleteContato(
            @Parameter(description = "Id do contato") @Positive Long id);
}
