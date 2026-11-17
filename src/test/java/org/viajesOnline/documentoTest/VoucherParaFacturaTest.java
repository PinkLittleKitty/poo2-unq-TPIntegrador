package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.VoucherParaFactura;

import static org.junit.jupiter.api.Assertions.*;

public class VoucherParaFacturaTest {
    private VoucherParaFactura voucher;

    @BeforeEach
    void setUp() {
        voucher = new VoucherParaFactura("000", "Jorge", 2, 15000.0);
    }

    @Test
    void creacionYGetters() {
        assertEquals("000", voucher.codTransaccion());
        assertEquals("Empresa Turismo", voucher.emisor());
        assertNotNull(voucher.fechaEmision());
        assertEquals("Voucher Factura", voucher.tipoDocumento());
        assertEquals("Jorge", voucher.getTitular());
        assertEquals(2, voucher.getCantidadDeServicios());
        assertEquals(15000.0, voucher.getMonto());
    }

    @Test
    void mostrarDetallesTest() {
        assertDoesNotThrow(() -> voucher.mostrarDetalles());
    }
}
