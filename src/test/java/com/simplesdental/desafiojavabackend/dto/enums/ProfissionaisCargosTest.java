package com.simplesdental.desafiojavabackend.dto.enums;

import com.simplesdental.desafiojavabackend.entity.enums.ProfissionaisCargos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ProfissionaisCargosTest {

    @ParameterizedTest
    @EnumSource(ProfissionaisCargos.class)
    public void testValues(ProfissionaisCargos cargo) {
        Assertions.assertNotNull(cargo);
    }

    @ParameterizedTest
    @EnumSource(value = ProfissionaisCargos.class, names = {"Desenvolvedor", "Designer"})
    public void testSpecificCargos(ProfissionaisCargos cargo) {
        Assertions.assertNotNull(cargo);
        Assertions.assertTrue(cargo == ProfissionaisCargos.Desenvolvedor || cargo == ProfissionaisCargos.Designer);
    }
}