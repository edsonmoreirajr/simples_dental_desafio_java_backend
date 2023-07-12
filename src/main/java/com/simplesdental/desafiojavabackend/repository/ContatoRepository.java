package com.simplesdental.desafiojavabackend.repository;

import com.simplesdental.desafiojavabackend.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
