package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.ComprobanteCbu;
import org.viajesOnline.documento.Documento;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComprobanteCbuTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ComprobanteCbu comprobanteCbu;

    @BeforeEach
    void setUp() {

        outputStreamCaptor.reset();

        System.setOut(new PrintStream(outputStreamCaptor));

        comprobanteCbu = new ComprobanteCbu("B745777C",1000.0
                                            ,"0110001230000011112222", "usuario.alias" );
    }

    @Test
    void procesarPagoExitosoHaceQueSeIngreseElVoucherAlGestor() {

        assertEquals("Comprobante Cbu",((ComprobanteCbu)comprobanteCbu).tipoDocumento());

        assertEquals("usuario.alias",((ComprobanteCbu)comprobanteCbu).obtenerAliasUsuario());

        assertEquals("0110001230000011112222",((ComprobanteCbu)comprobanteCbu).obtenerCbu());

        assertEquals(1000.0,((ComprobanteCbu)comprobanteCbu).monto(),0.001);

        assertEquals("Empresa Turismo",comprobanteCbu.emisor());

        assertEquals(LocalDate.now(),comprobanteCbu.fechaEmision().toLocalDate());

        comprobanteCbu.mostrarDetalles();

        String consolaOutput = outputStreamCaptor.toString(StandardCharsets.UTF_8);

        assertTrue(consolaOutput.contains("Codigo Transaccion: " + comprobanteCbu.codTransaccion()),
                "Fallo Codigo Transaccion. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Emisor: " + comprobanteCbu.emisor()),
                "Fallo Emisor. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Fecha: " + comprobanteCbu.fechaEmision().toLocalDate()),
                "Fallo Fecha. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Tipo Voucher: " + ((ComprobanteCbu)comprobanteCbu).tipoDocumento()),
                "Fallo Tipo Voucher. Lo que imprimió fue:\n" + consolaOutput);


        assertTrue(consolaOutput.contains("Alias Usuario: " + ((ComprobanteCbu)comprobanteCbu).obtenerAliasUsuario()),
                "Fallo Tipo Voucher. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Cbu: " + ((ComprobanteCbu) comprobanteCbu).obtenerCbu()),
                "Fallo Numero Tarjeta. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Total Pagado $: " + ((ComprobanteCbu)comprobanteCbu).monto()),
                "Fallo Total Pagado. Lo que imprimió fue:\n" + consolaOutput);
    }

    @AfterEach
    public void tearDown() {

        System.setOut(originalOut);
    }

}

