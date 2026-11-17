package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.VoucherReembolso;

import static org.junit.jupiter.api.Assertions.*;

public class VoucherReembolsoTest {
    private VoucherReembolso voucher;

    @BeforeEach
    void setUp() {
        voucher = new VoucherReembolso("000", 15000.0, 100.0);
    }

    @Test
    void creacionYGetters() {
        assertEquals("000", voucher.codTransaccion());
        assertEquals("Empresa Turismo", voucher.emisor());
        assertNotNull(voucher.fechaEmision());
        assertEquals("Voucher Reembolso", voucher.tipoDocumento());
        assertEquals(15000.0, voucher.obtenerCostoServicio());
        assertEquals(100.0, voucher.obtenerTasasAdministrativas());
    }

    @Test
    void mostrarDetallesTest() {
        assertDoesNotThrow(() -> voucher.mostrarDetalles());
    }
}
