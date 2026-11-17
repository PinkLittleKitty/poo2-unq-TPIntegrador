package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.ComprobanteFiscal;

import static org.junit.jupiter.api.Assertions.*;

public class ComprobanteFiscalTest {
    @Test
    void comprobanteTipoAConIva() {
        ComprobanteFiscal facturaA = new ComprobanteFiscal("000", "A", "Empresa", "1111", 15000.0);

        assertEquals("000", facturaA.codTransaccion());
        assertEquals("Empresa Turismo", facturaA.emisor());
        assertNotNull(facturaA.fechaEmision());
        assertEquals("Comprobante Fiscal", facturaA.tipoDocumento());
        assertEquals("A", facturaA.getTipoFactura());
        assertEquals("Empresa", facturaA.getNombreCliente());
        assertEquals("1111", facturaA.getIdentificacionFiscal());
        assertEquals(15000.0, facturaA.monto());
        assertEquals(2603.31, facturaA.getIva());
        assertEquals(12396.69, facturaA.getImporteNeto());
    }

    @Test
    void comprobanteTipoBConIva() {
        ComprobanteFiscal facturaB = new ComprobanteFiscal("000", "B", "Consumidor Final", "Consumidor Final", 15000.0);

        assertEquals("B", facturaB.getTipoFactura());
        assertEquals(15000.0, facturaB.monto());
        assertEquals(2603.31, facturaB.getIva());
        assertEquals(12396.69, facturaB.getImporteNeto());
    }

    @Test
    void mostrarDetallesTest() {
        ComprobanteFiscal factura = new ComprobanteFiscal("000", "B", "Cliente", "123", 500.0);
        assertDoesNotThrow(() -> factura.mostrarDetalles());
    }
}
