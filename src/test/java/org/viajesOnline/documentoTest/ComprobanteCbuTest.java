package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.ComprobanteCbu;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.VoucherParaPago;

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

        // 2. IMPORTANTE: Forza a que la consola use UTF-8 para evitar problemas con el "$"
        String consolaOutput = outputStreamCaptor.toString(StandardCharsets.UTF_8);

        //mensaje de error personalizado para saber QUÉ falló si el test da error
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
        // Al terminar el test, devolvemos System.out a su estado original
        System.setOut(originalOut);
    }

}

