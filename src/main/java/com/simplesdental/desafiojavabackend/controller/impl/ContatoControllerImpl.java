package com.simplesdental.desafiojavabackend.controller.impl;

import com.simplesdental.desafiojavabackend.controller.ContatoController;
import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.dto.request.ContatoRequest;
import com.simplesdental.desafiojavabackend.dto.response.PagedResponse;
import com.simplesdental.desafiojavabackend.service.ContatoService;
import com.simplesdental.desafiojavabackend.util.HateoasUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/contatos")
@RequiredArgsConstructor
@Validated
public class ContatoControllerImpl implements ContatoController {

    private final ContatoService contatoService;
    private final HateoasUtil<ContatoDto> hateoasUtil;
    private final HateoasUtil<Map<String, Object>> hateoasUtilMap;

    @GetMapping
    @Override
    public ResponseEntity<PagedResponse<Map<String, Object>>> getAllContatos(
            @RequestParam(name = "q") String texto,
            @RequestParam(name = "fields", required = false) String[] fields,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "nome", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {

        var contatos = contatoService.getAllContatos(pageable, texto, fields);

        return ResponseEntity.ok(hateoasUtilMap.buildResponseList(contatos));
    }

    @PostMapping
    @Override
    public ResponseEntity<ContatoDto> createContato(@RequestBody ContatoRequest contatoRequest) {

        var contatoDto = contatoService.createContato(contatoRequest);
        return ResponseEntity
                .created(hateoasUtil.getHateoasSelLik(ContatoControllerImpl.class, contatoDto.getId()))
                .body(contatoDto);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ResponseEntity<ContatoDto> updateContato(
            @PathVariable("id") Long id,
            @RequestBody ContatoRequest contatoRequest) {

        var contatoDto = contatoService.updateContato(id, contatoRequest);
        return ResponseEntity.ok(contatoDto);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ContatoDto> getContatoById(@PathVariable("id") Long id) {

        var contatoDto = contatoService.getContatoById(id);

        if (nonNull(contatoDto)) {
            return ResponseEntity.ok(contatoDto);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<Void> deleteContato(@PathVariable("id") Long id) {

        contatoService.deleteContato(id);
        return ResponseEntity.noContent().build();
    }
}
