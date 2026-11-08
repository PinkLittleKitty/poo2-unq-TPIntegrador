package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.viajesOnline.documento.ComprobanteCbu;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.VoucherParaPago;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class VoucherPagoTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Documento voucherParaPago;

    @BeforeEach
    void setUp() {

        outputStreamCaptor.reset();

        System.setOut(new PrintStream(outputStreamCaptor));

        voucherParaPago = new VoucherParaPago("B744777C","1234567891234567"
                                            , "111", 1000.0);
    }

    @Test
    void procesarPagoExitosoHaceQueSeIngreseElVoucherAlGestor() {

        assertEquals(1000.0,((VoucherParaPago)voucherParaPago).monto(),0.001);

        assertEquals("1234567891234567",((VoucherParaPago) voucherParaPago).numeroTarjeta());

        assertEquals("Voucher Pago",((VoucherParaPago)voucherParaPago).tipoDocumento());

        assertEquals("B744777C",voucherParaPago.codTransaccion());

        assertEquals("Empresa Turismo",voucherParaPago.emisor());

        assertEquals(LocalDate.now(),voucherParaPago.fechaEmision().toLocalDate());

        voucherParaPago.mostrarDetalles();

        String consolaOutput = outputStreamCaptor.toString(StandardCharsets.UTF_8);

        assertTrue(consolaOutput.contains("Codigo Transaccion: " + voucherParaPago.codTransaccion()),
                "Fallo Codigo Transaccion. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Emisor: " + voucherParaPago.emisor()),
                "Fallo Emisor. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Fecha: " + voucherParaPago.fechaEmision().toLocalDate()),
                "Fallo Fecha. Lo que imprimió fue:\n" + consolaOutput);


        assertTrue(consolaOutput.contains("Tipo Voucher: " + ((VoucherParaPago)voucherParaPago).tipoDocumento()),
                "Fallo Tipo Voucher. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Numero Tarjeta: " + ((VoucherParaPago) voucherParaPago).numeroTarjeta()),
                "Fallo Numero Tarjeta. Lo que imprimió fue:\n" + consolaOutput);

        assertTrue(consolaOutput.contains("Total Pagado: $ " + ((VoucherParaPago)voucherParaPago).monto()),
                "Fallo Total Pagado. Lo que imprimió fue:\n" + consolaOutput);
    }

    @AfterEach
    public void tearDown() {

        System.setOut(originalOut);
    }

}

