package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.ReciboDeSolicitud;

import static org.junit.jupiter.api.Assertions.*;

public class ReciboSolicitudTest {
    private ReciboDeSolicitud recibo;

    @BeforeEach
    void setUp() {
        recibo = new ReciboDeSolicitud("000", "Confirmada", "test@test.com", 15000.0, 3);
    }

    @Test
    void creacionYGetters() {
        assertEquals("000", recibo.codTransaccion());
        assertEquals("Empresa Turismo", recibo.emisor());
        assertNotNull(recibo.fechaEmision());
        assertEquals("Recibo Solicitud", recibo.tipoDocumento());
        assertEquals("Confirmada", recibo.getEstadoDeSolicitud());
        assertEquals("test@test.com", recibo.getEmailDestinatario());
        assertEquals(15000.0, recibo.getMontoTotal());
        assertEquals(3, recibo.getCantidadItems());
    }

    @Test
    void mostrarDetallesTest() {
        assertDoesNotThrow(() -> recibo.mostrarDetalles());
    }
}
