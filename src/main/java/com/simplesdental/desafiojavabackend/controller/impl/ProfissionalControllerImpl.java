package com.simplesdental.desafiojavabackend.controller.impl;

import com.simplesdental.desafiojavabackend.controller.ProfissionalController;
import com.simplesdental.desafiojavabackend.dto.ProfissionalDto;
import com.simplesdental.desafiojavabackend.dto.request.ProfissionalRequest;
import com.simplesdental.desafiojavabackend.dto.response.PagedResponse;
import com.simplesdental.desafiojavabackend.service.ProfissionalService;
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
@RequestMapping("/api/v1/profissionais")
@RequiredArgsConstructor
@Validated
public class ProfissionalControllerImpl implements ProfissionalController {

    private final ProfissionalService profissionalService;
    private final HateoasUtil<ProfissionalDto> hateoasUtil;
    private final HateoasUtil<Map<String, Object>> hateoasUtilMap;

    @GetMapping
    @Override
    public ResponseEntity<PagedResponse<Map<String, Object>>> getAllProfissionais(
            @RequestParam(name = "q") String texto,
            @RequestParam(name = "fields", required = false) String[] fields,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "nome", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "cargo", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {

        var profissionais = profissionalService.getAllProfissionais(pageable, texto, fields);

        return ResponseEntity.ok(hateoasUtilMap.buildResponseList(profissionais));
    }

    @PostMapping
    @Override
    public ResponseEntity<ProfissionalDto> createProfissional(@RequestBody ProfissionalRequest profissionalRequest) {

        var profissionalDto = profissionalService.createProfissional(profissionalRequest);
        return ResponseEntity
                .created(hateoasUtil.getHateoasSelLik(ProfissionalControllerImpl.class, profissionalDto.getId()))
                .body(profissionalDto);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProfissionalDto> updateProfissional(
            @PathVariable("id") Long id,
            @RequestBody ProfissionalRequest profissionalRequest) {

        var profissionalDto = profissionalService.updateProfissional(id, profissionalRequest);
        return ResponseEntity.ok(profissionalDto);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProfissionalDto> getProfissionalById(@PathVariable("id") Long id) {

        var profissionalDto = profissionalService.getProfissionalById(id);

        if (nonNull(profissionalDto)) {
            return ResponseEntity.ok(profissionalDto);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProfissionalDto> deleteProfissional(@PathVariable("id") Long id) {

        var profissionalDto = profissionalService.deleteProfissional(id);
        return ResponseEntity.ok(profissionalDto);
    }
}