package com.simplesdental.desafiojavabackend.repository;

import com.simplesdental.desafiojavabackend.entity.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = "SELECT " +
            "   c.id as id, " +
            "   CASE WHEN ARRAY_LENGTH(:fields, 1) = 0 THEN c.nome ELSE CASE WHEN 'nome' = ANY (:fields) THEN c.nome ELSE NULL END END as nome, " +
            "   CASE WHEN ARRAY_LENGTH(:fields, 1) = 0 THEN c.contato ELSE CASE WHEN 'contato' = ANY (:fields) THEN c.contato ELSE NULL END END as contato, " +
            "   CASE WHEN ARRAY_LENGTH(:fields, 1) = 0 THEN c.created_date ELSE CASE WHEN 'created_date' = ANY (:fields) THEN c.created_date ELSE NULL END END as createdDate " +
            "FROM contato c " +
            "WHERE c.nome ILIKE CONCAT('%', :texto, '%') OR c.contato ILIKE CONCAT('%', :texto, '%')",
            countQuery = "SELECT COUNT(*) FROM contato",
            nativeQuery = true)
    Page<Map<String, Object>> findByTextoAndFieldsDynamic(@Param("texto") String texto,
                                                          @Param("fields") String[] fields,
                                                          Pageable pageable);

}
