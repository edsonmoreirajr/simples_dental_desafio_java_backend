package com.simplesdental.desafiojavabackend.repository;

import com.simplesdental.desafiojavabackend.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}