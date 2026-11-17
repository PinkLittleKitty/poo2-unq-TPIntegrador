package org.viajesOnline.metodoPagoTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.metodoPago.ApiBilleteraVirtual;
import org.viajesOnline.metodoPago.MetodoPagoBilleteraVirtual;

class MetodoPagoBilleteraVirtualTest {

    private ApiBilleteraVirtual apiBVirtualMock;
    private GestorDeDocumentos gestorMock;
    private MetodoPagoBilleteraVirtual billeteraVirtual;
    private double saldoPrueba = 5000.0;

    @BeforeEach
    void setUp() {

        apiBVirtualMock = mock(ApiBilleteraVirtual.class);
        gestorMock = mock(GestorDeDocumentos.class);
        billeteraVirtual = new MetodoPagoBilleteraVirtual(saldoPrueba,apiBVirtualMock);
    }

    @Test
    void procesarPagoBilleteraExitosoDebeEjecutarTodasLasEtapasYNotificarPush() {

        when(apiBVirtualMock.validar(anyDouble())).thenReturn(true);
        when(apiBVirtualMock.reservar()).thenReturn(true);

        assertDoesNotThrow(() -> {billeteraVirtual.procesarPago();});

        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock).reservar();
        verify(apiBVirtualMock).ejecutar(anyDouble());
        verify(apiBVirtualMock).notificar();

    }

    @Test
    void debeLanzarExcepcionSiNoHaySaldoSuficiente() {

        when(apiBVirtualMock.validar(anyDouble())).thenReturn(false);

        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            billeteraVirtual.procesarPago();});

        assertEquals("Transferencia denegada, saldo insuficiente en cuenta", excepcion.getMessage());

        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock, never()).reservar();
        verify(apiBVirtualMock, never()).ejecutar(anyDouble());
        verify(apiBVirtualMock, never()).notificar();
    }

    @Test
    void debeLanzarExcepcionSiFallaElBloqueoDeSaldo() {

        when(apiBVirtualMock.validar(anyDouble())).thenReturn(true);
        when(apiBVirtualMock.reservar()).thenReturn(false);

        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            billeteraVirtual.procesarPago();});

        assertEquals("Bloqueo del saldo hasta confirmar", excepcion.getMessage());

        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock).reservar();
        verify(apiBVirtualMock, never()).ejecutar(saldoPrueba);
        verify(apiBVirtualMock, never()).notificar();
    }

    @Test
    void procesarPagoBilleteraExitosoDebeEjecutarConElMontoCorrectoYNotificar() {

        when(apiBVirtualMock.validar(anyDouble())).thenReturn(true);
        when(apiBVirtualMock.reservar()).thenReturn(true);

        assertDoesNotThrow(() -> {
            billeteraVirtual.procesarPago();});

        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock).reservar();
        verify(apiBVirtualMock).ejecutar(anyDouble());
        verify(apiBVirtualMock).notificar();
    }
}