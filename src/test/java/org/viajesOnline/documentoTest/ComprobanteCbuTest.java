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
    private Documento comprobandteCbu;

    @BeforeEach
    void setUp() {

        outputStreamCaptor.reset();

        System.setOut(new PrintStream(outputStreamCaptor));

        comprobandteCbu = new ComprobanteCbu("B745777C",1000
                                            ,"0110001230000011112222", "usuario.alias" );
    }

    @Test
    void procesarPagoExitosoHaceQueSeIngreseElVoucherAlGestor() {

        assertEquals(1000.0,comprobandteCbu.monto(),0.001);

        assertEquals("Comprobante Cbu",((ComprobanteCbu)comprobandteCbu).tipoDocumento());

        assertEquals("0110001230000011112222",((ComprobanteCbu)comprobandteCbu).obtenerCbu());

        assertEquals("usuario.alias",((ComprobanteCbu)comprobandteCbu).obtenerAliasUsuario());

        assertEquals("Empresa Turismo",comprobandteCbu.emisor());

        assertEquals(LocalDate.now(),comprobandteCbu.fechaEmision().toLocalDate());

        comprobandteCbu.mostrarDetalles();

        String consolaOutput = outputStreamCaptor.toString(StandardCharsets.UTF_8);

        assertTrue(consolaOutput.contains("Codigo Transaccion: " + comprobandteCbu.codTransaccion()),
                "Fallo Codigo Transaccion. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Emisor: " + comprobandteCbu.emisor()),
                "Fallo Emisor. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Fecha: " + comprobandteCbu.fechaEmision().toLocalDate()),
                "Fallo Fecha. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Total Pagado: $" + comprobandteCbu.monto()),
                "Fallo Total Pagado. Lo que imprimió fue:\n" + consolaOutput);


        assertTrue(consolaOutput.contains("Alias Usuario: " + ((ComprobanteCbu)comprobandteCbu).obtenerAliasUsuario()),
                "Fallo Tipo Voucher. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Cbu: " + ((ComprobanteCbu) comprobandteCbu).obtenerCbu()),
                "Fallo Numero Tarjeta. Lo que imprimió fue:\n" + consolaOutput);
    }

    @AfterEach
    public void tearDown() {

        System.setOut(originalOut);
    }

}

