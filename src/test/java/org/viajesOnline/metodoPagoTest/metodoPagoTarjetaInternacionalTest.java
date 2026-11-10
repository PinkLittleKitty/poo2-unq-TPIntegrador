package org.viajesOnline.metodoPagoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;

import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.documento.VoucherParaPago;

import org.viajesOnline.metodoPago.ApiTarjetaCredito;
import org.viajesOnline.metodoPago.MetodoPago;
import org.viajesOnline.metodoPago.MetodoPagoTarjetaInternacional;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class metodoPagoTarjetaInternacionalTest {

    private ApiTarjetaCredito apiVisaInt;
    private ApiTarjetaCredito apiMastercardInt;

    private MetodoPagoTarjetaInternacional tarjetaInternacional;
    private MetodoPagoTarjetaInternacional otraTarjetaInternacional;

    private GestorDeDocumentos gestorMock;
    private VoucherParaPago voucherParaPago;

    @BeforeEach
    void setUp() {

        voucherParaPago = mock(VoucherParaPago.class);
        gestorMock = mock(GestorDeDocumentos.class);
        apiVisaInt = mock(ApiTarjetaCredito.class);
        apiMastercardInt = mock(ApiTarjetaCredito.class);

        tarjetaInternacional = new MetodoPagoTarjetaInternacional(gestorMock,500.2,"1234567891234567", "111",
                YearMonth.of(2020, 12), apiVisaInt);

        otraTarjetaInternacional = new MetodoPagoTarjetaInternacional(gestorMock,500.2,"1234567891111561", "555",
                YearMonth.of(2030, 12), apiMastercardInt);

    }

    @Test
    void procesarPagoExitosoParaTarjetaInternacionalVisaDebeEjecutarTodoElFlujoCorrectamente() {

        when(apiVisaInt.validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12))))
                .thenReturn(true);

        when(apiVisaInt.reservar()).thenReturn(true);
        when(apiVisaInt.ejecutar()).thenReturn(500.2);

        assertDoesNotThrow(() -> {tarjetaInternacional.procesarPago();});

        assertEquals(500.2, tarjetaInternacional.monto(), 0.001);

        verify(apiVisaInt).validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12)));
        verify(apiVisaInt).reservar();
        verify(apiVisaInt).ejecutar();

        String codigoGenerado = (tarjetaInternacional).getUltimoCodigoTransaccion();

        assertNotNull(codigoGenerado);
        assertEquals(8, codigoGenerado.length());

        verify(gestorMock).registrarDocumento(any(VoucherParaPago.class));
    }

    @Test
    void sePasaUnaFechaDeTarjetaVisaParaQueSeValideComoVencida() {

        when(apiVisaInt.validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12))))
                .thenReturn(false);


        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            tarjetaInternacional.validarDatos();});

        assertEquals("Tarjeta de credito denegada, no se pudo validar", excepcion.getMessage());

        verify(apiVisaInt).validar("1234567891234567", "111", YearMonth.of(2020, 12));

        assertThrows(MetodoPagoExcepcion.class, () -> {tarjetaInternacional.procesarPago();});

        verify(apiVisaInt, never()).reservar();
        verify(apiVisaInt, never()).ejecutar();
    }

    @Test
    void procesarPagoDebeFallarEnValidacionYNoEjecutarElRestoParaTarjetaVisa() {

        when(apiVisaInt.validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12))))
                .thenReturn(false);

        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            tarjetaInternacional.procesarPago();});

        assertEquals("Tarjeta de credito denegada, no se pudo validar", excepcion.getMessage());

        verify(apiVisaInt).validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12)));

        verify(apiVisaInt, never()).reservar();
        verify(apiVisaInt, never()).ejecutar();
    }

    @Test
    void procesarPagoExitosoDebeEjecutarTodoElFlujoCorrectamente() {

        when(apiMastercardInt.validar(("1234567891111561"), ("555"), YearMonth.of(2030, 12)))
                .thenReturn(true);

        when(apiMastercardInt.reservar()).thenReturn(true);
        when(apiMastercardInt.ejecutar()).thenReturn(1501.50);

        assertDoesNotThrow(() -> {otraTarjetaInternacional.procesarPago();});

        assertEquals(1501.50, otraTarjetaInternacional.monto(), 0.001);

        verify(apiMastercardInt).validar("1234567891111561", "555", YearMonth.of(2030, 12));
        verify(apiMastercardInt).reservar();
        verify(apiMastercardInt).ejecutar();

        String codigoGenerado = (otraTarjetaInternacional).getUltimoCodigoTransaccion();

        assertNotNull(codigoGenerado);
        assertEquals(8, codigoGenerado.length());

        verify(gestorMock).registrarDocumento(any(VoucherParaPago.class));
    }

    @Test
    void debeLanzarExcepcionSiNoSePuedenReservarLosFondos() {

        when(apiMastercardInt.validar(("1234567891111561"), ("555"), YearMonth.of(2030, 12)))
                .thenReturn(true);

        when(apiMastercardInt.reservar()).thenReturn(false);

        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            otraTarjetaInternacional.procesarPago();
        });

        assertEquals("Tarjeta no autorizada por el banco", excepcion.getMessage());

        verify(apiMastercardInt).validar(("1234567891111561"), ("555"), YearMonth.of(2030, 12));
        verify(apiMastercardInt).reservar();
        verify(apiMastercardInt, never()).ejecutar();
    }

    @Test
    public void testSobreTarjetaMastercardSeProcesaExitosamenteElPago() {

        when(apiMastercardInt.validar(("1234567891111561"), ("555"), YearMonth.of(2030, 12)))
                .thenReturn(true);

        when(apiMastercardInt.reservar()).thenReturn(true);
        when(apiMastercardInt.ejecutar()).thenReturn(1501.50);

        assertDoesNotThrow(() -> {otraTarjetaInternacional.procesarPago();});

        assertEquals(1501.50, otraTarjetaInternacional.monto(), 0.001);

        verify(apiMastercardInt).validar("1234567891111561", "555", YearMonth.of(2030, 12));
        verify(apiMastercardInt).reservar();
        verify(apiMastercardInt).ejecutar();

        String codigoGenerado = (otraTarjetaInternacional).getUltimoCodigoTransaccion();

        assertNotNull(codigoGenerado);
        assertEquals(8, codigoGenerado.length());

        verify(gestorMock).registrarDocumento(any(VoucherParaPago.class));
    }
}




