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

    private MetodoPago tarjetaInternacional;
    private MetodoPago otraTarjetaInternacional;

    private GestorDeDocumentos gestorMock;
    private Documento voucherParaPago;

    @BeforeEach
    void setUp() {

        voucherParaPago = mock(Documento.class);
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

        //Verifica explícitamente que no falle que todo esta
        //efectivamente bien
        assertDoesNotThrow(() -> {tarjetaInternacional.procesarPago();});

        assertEquals(500.2, tarjetaInternacional.monto(), 0.001);

        //Verificamos que se hayan invocado los métodos de la API en orden
        verify(apiVisaInt).validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12)));
        verify(apiVisaInt).reservar();
        verify(apiVisaInt).ejecutar();

        //Assert: Verificamos usando el getter que el código NO sea nulo y se haya generado
        String codigoGenerado = (tarjetaInternacional).getUltimoCodigoTransaccion();

        assertNotNull(codigoGenerado);
        assertEquals(8, codigoGenerado.length());

        //o que hace el verify en Mockito?
        //Le pregunta al mock del gestor: "Che, ¿la tarjeta te llamó al método registrarVoucherParaPago?".
        verify(gestorMock).registrarVoucher(any(VoucherParaPago.class));

    }

    @Test
    void sePasaUnaFechaDeTarjetaVisaParaQueSeValideComoVencida() {

        when(apiVisaInt.validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12))))
                .thenReturn(false);


        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            tarjetaInternacional.validarDatos();
        });

        assertEquals("Tarjeta de credito denegada, no se pudo validar", excepcion.getMessage());

        verify(apiVisaInt).validar("1234567891234567", "111", YearMonth.of(2020, 12));

        assertThrows(MetodoPagoExcepcion.class, () -> {tarjetaInternacional.procesarPago();});

        verify(apiVisaInt, never()).reservar();
        verify(apiVisaInt, never()).ejecutar();

    }

    @Test
    void procesarPagoDebeFallarEnValidacionYNoEjecutarElRestoParaTarjetaVisa() {

        // 1. Arrange: Configuramos el mock para que la validación falle
        when(apiVisaInt.validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12))))
                .thenReturn(false);

        // 2. Act & Assert: El proceso completo (procesarPago) debe lanzar la excepción
        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            tarjetaInternacional.procesarPago();
        });

        assertEquals("Tarjeta de credito denegada, no se pudo validar", excepcion.getMessage());

        // 3. Verificamos que se llamó a validar...
        verify(apiVisaInt).validar(("1234567891234567"), ("111"), (YearMonth.of(2020, 12)));

        //PERO verificamos con "never()" que NUNCA avanzó a los siguientes pasos
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

        // 4. Assert 2: Verificamos que se hayan invocado los métodos de la API en orden
        verify(apiMastercardInt).validar("1234567891111561", "555", YearMonth.of(2030, 12));
        verify(apiMastercardInt).reservar();
        verify(apiMastercardInt).ejecutar();

        //Assert: Verificamos usando el getter que el código NO sea nulo y se haya generado
        String codigoGenerado = (otraTarjetaInternacional).getUltimoCodigoTransaccion();

        assertNotNull(codigoGenerado);
        assertEquals(8, codigoGenerado.length());

        //o que hace el verify en Mockito?
        //Le pregunta al mock del gestor: "Che, ¿la tarjeta te llamó al método registrarVoucherParaPago?".
        verify(gestorMock).registrarVoucher(any(VoucherParaPago.class));

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

        // Verificamos que se intentó validar y reservar, pero NUNCA se llegó a ejecutar la transacción
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

        // 2. Act: Ejecutamos el flujo completo del Template Method
        assertDoesNotThrow(() -> {otraTarjetaInternacional.procesarPago();});

        // 3. Assert 1: Verificamos resultados de estado (monto)
        assertEquals(1501.50, otraTarjetaInternacional.monto(), 0.001);

        // 4. Assert 2: Verificamos que se hayan invocado los métodos de la API en orden
        verify(apiMastercardInt).validar("1234567891111561", "555", YearMonth.of(2030, 12));
        verify(apiMastercardInt).reservar();
        verify(apiMastercardInt).ejecutar();

        //Assert: Verificamos usando el getter que el código NO sea nulo y se haya generado
        String codigoGenerado = (otraTarjetaInternacional).getUltimoCodigoTransaccion();

        assertNotNull(codigoGenerado);
        assertEquals(8, codigoGenerado.length());

        //o que hace el verify en Mockito?
        //Le pregunta al mock del gestor: "Che, ¿la tarjeta te llamó al método registrarVoucherParaPago?".
        verify(gestorMock).registrarVoucher(any(VoucherParaPago.class));


    }
}




