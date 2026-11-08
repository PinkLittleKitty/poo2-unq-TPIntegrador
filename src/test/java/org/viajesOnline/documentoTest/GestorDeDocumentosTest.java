package org.viajesOnline.documentoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.ComprobanteCbu;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.documento.VoucherParaPago;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;

import java.util.List;

public class GestorDeDocumentosTest {

    private GestorDeDocumentos gestor;
    private VoucherParaPago voucherParaPagoMock;
    private ComprobanteCbu comprobanteCbuMock;

    @BeforeEach
    void setUp() {

        voucherParaPagoMock = mock(VoucherParaPago.class);
        comprobanteCbuMock = mock(ComprobanteCbu.class);
        gestor = new GestorDeDocumentos();
    }

    @Test
    void seLeIngresaUNvoucherAGestorYseValidaQueTieneUno() {

        gestor.registrarDocumento((Documento)voucherParaPagoMock);
        gestor.registrarDocumento((Documento)comprobanteCbuMock);

        assertEquals(List.of(voucherParaPagoMock,comprobanteCbuMock), gestor.obtenerDocumentos());
        assertTrue(gestor.obtenerDocumentos().contains(voucherParaPagoMock));
        assertTrue(gestor.obtenerDocumentos().contains(comprobanteCbuMock));

        assertEquals(2, gestor.cantidadDocumentos());
    }

}
