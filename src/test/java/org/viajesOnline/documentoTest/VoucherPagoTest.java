package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.documento.VoucherParaPago;
import org.viajesOnline.metodoPago.ApiTarjetaCredito;
import org.viajesOnline.metodoPago.MetodoPago;
import org.viajesOnline.metodoPago.MetodoPagoTarjetaInternacional;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class VoucherPagoTest {

    private ApiTarjetaCredito apiVisaInt;

    //MetodoPagoTarjetaInternacional
    private MetodoPago tarjetaInternacionalMock;

    private GestorDeDocumentos gestorMock;
    private Documento voucherParaPago;

    @BeforeEach
    void setUp() {

        tarjetaInternacionalMock = mock(MetodoPago.class);
        gestorMock = mock(GestorDeDocumentos.class);
        apiVisaInt = mock(ApiTarjetaCredito.class);
        voucherParaPago = new VoucherParaPago("84920473",("1234567891234567"), ("111"), 1000);

        //tarjetaInternacional = new MetodoPagoTarjetaInternacional(gestorMock,500.2,"1234567891234567", "111",
        // YearMonth.of(2020, 12), apiVisaInt);

    }

    @Test
    void procesarPagoExitosoParaTarjetaIntenacionalVisaDebeEjecutarTodoElFlujoCorrectamente() {

        when(apiVisaInt.validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12))))
                .thenReturn(true);

        when(apiVisaInt.reservar()).thenReturn(true);
        when(apiVisaInt.ejecutar()).thenReturn(1000.0);



        assertEquals(1000.0,voucherParaPago.monto(),0.001);
        assertEquals("1234567891234567",((VoucherParaPago) voucherParaPago).numeroTarjeta());

        assertEquals("Voucher Pago",((VoucherParaPago)voucherParaPago).tipoDocumento());

        assertEquals("84920473",((VoucherParaPago)voucherParaPago).codigoAutorizacion());

    }



}
