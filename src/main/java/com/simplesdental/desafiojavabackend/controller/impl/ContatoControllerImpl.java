package com.simplesdental.desafiojavabackend.controller.impl;

import com.simplesdental.desafiojavabackend.controller.ContatoController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contatos")
@RequiredArgsConstructor
public class ContatoControllerImpl implements ContatoController {
}
