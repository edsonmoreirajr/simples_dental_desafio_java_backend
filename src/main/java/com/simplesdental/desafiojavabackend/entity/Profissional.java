package com.simplesdental.desafiojavabackend.entity;

import com.simplesdental.desafiojavabackend.entity.enums.ProfissionaisCargos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "nome", nullable = false, length = 200)
    String nome;

    @NotNull
    @Column(name = "profissionais_cargos", length = 14)
    @Enumerated(EnumType.STRING)
    private ProfissionaisCargos cargo;

    @NotNull
    @Column(name = "nascimento", nullable = false)
    private Date nascimento;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
