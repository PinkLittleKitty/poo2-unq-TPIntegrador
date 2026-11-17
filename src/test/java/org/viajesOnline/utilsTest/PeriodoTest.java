package org.viajesOnline.utilsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodoTest {
    private Periodo periodo;

    @BeforeEach
    void setUp() {
        periodo = new Periodo(LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20));
    }

    @Test
    void creacionValida() {
        assertEquals(LocalDate.of(2026, 1, 10), periodo.fechaInicio());
        assertEquals(LocalDate.of(2026, 1, 20), periodo.fechaFin());
    }

    @Test
    void creacionInvalida() {
        assertThrows(NullPointerException.class, () -> new Periodo(null, LocalDate.of(2026, 1, 1)));
        assertThrows(NullPointerException.class, () -> new Periodo(LocalDate.of(2026, 1, 1), null));
        assertThrows(IllegalArgumentException.class, () -> new Periodo(LocalDate.of(2026, 10, 1), LocalDate.of(2026, 1, 1)));
    }

    @Test
    void incluyeFechas() {
        assertTrue(periodo.incluye(LocalDate.of(2026, 1, 10)));
        assertTrue(periodo.incluye(LocalDate.of(2026, 1, 15)));
        assertTrue(periodo.incluye(LocalDate.of(2026, 1, 20)));
        assertFalse(periodo.incluye(LocalDate.of(2026, 1, 9)));
        assertFalse(periodo.incluye(LocalDate.of(2026, 1, 21)));
    }

    @Test
    void seSuperpone() {
        assertTrue(periodo.seSuperponeCon(new Periodo(LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20))));
        assertTrue(periodo.seSuperponeCon(new Periodo(LocalDate.of(2026, 1, 5), LocalDate.of(2026, 1, 12))));
        assertTrue(periodo.seSuperponeCon(new Periodo(LocalDate.of(2026, 1, 18), LocalDate.of(2026, 1, 25))));
        assertTrue(periodo.seSuperponeCon(new Periodo(LocalDate.of(2026, 1, 13), LocalDate.of(2026, 1, 15))));
        assertTrue(periodo.seSuperponeCon(new Periodo(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31))));

    }
}
