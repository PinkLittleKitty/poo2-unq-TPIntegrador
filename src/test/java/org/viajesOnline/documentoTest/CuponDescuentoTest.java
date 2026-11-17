package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.CuponDescuento;

import static org.junit.jupiter.api.Assertions.*;

public class CuponDescuentoTest {
    private CuponDescuento cupon;

    @BeforeEach
    void setUp() {
        cupon = new CuponDescuento("A113", 15.0);
    }

    @Test
    void creacionYGetters() {
        assertEquals("A113", cupon.codTransaccion());
        assertEquals("Empresa Turismo", cupon.emisor());
        assertNotNull(cupon.fechaEmision());
        assertEquals("Cupon Descuento", cupon.tipoDocumento());
        assertEquals(15.0, cupon.porcentajeDescuento());
    }

    @Test
    void mostrarDetallesTest() {
        assertDoesNotThrow(() -> cupon.mostrarDetalles());
    }
}
