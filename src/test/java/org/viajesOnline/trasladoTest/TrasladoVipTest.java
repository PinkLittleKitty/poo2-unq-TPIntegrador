package org.viajesOnline.trasladoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.traslados.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrasladoVipTest {

    private TrasladoVip trasladoVip;

    @BeforeEach
    void setUp() {

        trasladoVip = new TrasladoVip(100f,20f);
    }

    @Test
    void cargoBaseYPorcentajeFijo() {

        assertEquals(100f, trasladoVip.cargoBase(), 0.001f);

        assertEquals(0.80f, trasladoVip.porcentajeFijo(), 0.001f);
    }

    @Test
    void calculaUnCosto() {

        assertEquals(180f, trasladoVip.calcularCosto(100), 0.001f);
    }
}
