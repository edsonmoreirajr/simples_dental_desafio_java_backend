package com.simplesdental.desafiojavabackend.repository;

import com.simplesdental.desafiojavabackend.entity.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    @Query(value = "SELECT " +
            "   p.id as id, " +
            "   CASE WHEN :fields IS NULL OR array_length(:fields, 1) = 0 THEN p.nome ELSE CASE WHEN 'nome' = ANY (:fields) THEN p.nome ELSE NULL END END as nome, " +
            "   CASE WHEN :fields IS NULL OR array_length(:fields, 1) = 0 THEN p.cargo ELSE CASE WHEN 'cargo' = ANY (:fields) THEN p.cargo ELSE NULL END END as cargo, " +
            "   CASE WHEN :fields IS NULL OR array_length(:fields, 1) = 0 THEN p.nascimento ELSE CASE WHEN 'nascimento' = ANY (:fields) THEN p.nascimento ELSE NULL END END as nascimento, " +
            "   CASE WHEN :fields IS NULL OR array_length(:fields, 1) = 0 THEN p.created_date ELSE CASE WHEN 'created_date' = ANY (:fields) THEN p.created_date ELSE NULL END END as createdDate " +
            "FROM profissional p " +
            "WHERE p.nome ILIKE CONCAT('%', :texto, '%') OR p.cargo ILIKE CONCAT('%', :texto, '%')",
            countQuery = "SELECT COUNT(*) FROM profissional",
            nativeQuery = true)
    Page<Map<String, Object>> findByTextoAndFieldsDynamic(@Param("texto") String texto,
                                                          @Param("fields") String[] fields,
                                                          Pageable pageable);
}