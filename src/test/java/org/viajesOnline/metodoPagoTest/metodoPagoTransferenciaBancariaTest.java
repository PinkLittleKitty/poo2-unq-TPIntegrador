package org.viajesOnline.metodoPagoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.gestorDeRecibos.ComprobanteCbu;
import org.viajesOnline.gestorDeRecibos.GestorDeRecibos;
import org.viajesOnline.metodoPago.ApiTransferenciaBancaria;
import org.viajesOnline.metodoPago.MetodoPago;
import org.viajesOnline.metodoPago.MetodoPagoTransferenciaBancaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class MetodoPagoTransferenciaBancariaTest {

    private ApiTransferenciaBancaria apiTBancariaMock;
    private GestorDeRecibos gestorMock;
    private MetodoPago transferenciaBancaria;

    @BeforeEach
    void setUp() {

        apiTBancariaMock = mock(ApiTransferenciaBancaria.class);
        gestorMock = mock(GestorDeRecibos.class);

        transferenciaBancaria = new MetodoPagoTransferenciaBancaria(
                gestorMock,500.2, "0110001230000011112222", "usuario.alias",
                apiTBancariaMock
        );
    }

    @Test
    void procesarPagoTransferenciaExitosoDebeRegistrarComprobante() {

        when(apiTBancariaMock.validar("0110001230000011112222", "usuario.alias")).thenReturn(true);
        when(apiTBancariaMock.reservar()).thenReturn(true);
        when(apiTBancariaMock.ejecutar()).thenReturn(5000.0); // Retorna el monto

        assertDoesNotThrow(() -> {
            transferenciaBancaria.procesarPago();
        });

        verify(gestorMock).registrarComprobanteCbu(any(ComprobanteCbu.class));

        verify(apiTBancariaMock).validar("0110001230000011112222", "usuario.alias");
        verify(apiTBancariaMock).reservar();
        verify(apiTBancariaMock).ejecutar();

        assertNotNull(transferenciaBancaria.getUltimoCodigoTransaccion());
        assertEquals(8, transferenciaBancaria.getUltimoCodigoTransaccion().length());
    }

    @Test
    void debeLanzarExcepcionSiFallaLaValidacionDelCbu() {

        when(apiTBancariaMock.validar("0110001230000011112222", "usuario.alias")).thenReturn(false);

        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            transferenciaBancaria.procesarPago();});

        assertEquals("Transferencia bancaria denegada, no se pudo validar alias / cbu", excepcion.getMessage());

        verify(apiTBancariaMock).validar("0110001230000011112222", "usuario.alias");
        verify(apiTBancariaMock, never()).reservar();
        verify(apiTBancariaMock, never()).ejecutar();
        verify(gestorMock, never()).registrarComprobanteCbu(any());
    }

    @Test
    void debeLanzarExcepcionSiFallaLaReservaDeFondos() {

        when(apiTBancariaMock.validar("0110001230000011112222", "usuario.alias")).thenReturn(true);
        when(apiTBancariaMock.reservar()).thenReturn(false);

        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            transferenciaBancaria.procesarPago();});

        assertEquals("Denegada no aplica transferencia directa", excepcion.getMessage());

        verify(apiTBancariaMock).validar("0110001230000011112222", "usuario.alias");
        verify(apiTBancariaMock).reservar();
        verify(apiTBancariaMock, never()).ejecutar();
        verify(gestorMock, never()).registrarComprobanteCbu(any());
    }
}
