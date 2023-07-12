package com.simplesdental.desafiojavabackend.controller.impl;

import com.simplesdental.desafiojavabackend.controller.ProfissionalController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profissionais")
@RequiredArgsConstructor
public class ProfissionalControllerImpl implements ProfissionalController {
}