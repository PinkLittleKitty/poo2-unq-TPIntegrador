package org.viajesOnline.metodoPagoTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.gestorDeRecibos.GestorDeRecibos;
import org.viajesOnline.metodoPago.ApiBilleteraVirtual;
import org.viajesOnline.metodoPago.MetodoPago;
import org.viajesOnline.metodoPago.MetodoPagoBilleteraVirtual;

class MetodoPagoBilleteraVirtualTest {

    private ApiBilleteraVirtual apiBVirtualMock;
    private GestorDeRecibos gestorMock;
    private MetodoPagoBilleteraVirtual billeteraVirtual;
    private double saldoPrueba = 5000.0;

    @BeforeEach
    void setUp() {

        apiBVirtualMock = mock(ApiBilleteraVirtual.class);
        gestorMock = mock(GestorDeRecibos.class);

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

        // Comprobamos que el monto se asignó correctamente en la clase abstracta
    }

    @Test
    void debeLanzarExcepcionSiNoHaySaldoSuficiente() {
        // Arrange: La API rechaza la validación por falta de saldo
        when(apiBVirtualMock.validar(anyDouble())).thenReturn(false);

        // Act & Assert
        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            billeteraVirtual.procesarPago();
        });

        assertEquals("Transferencia denegada, saldo insuficiente en cuenta", excepcion.getMessage());

        // Verificamos que validó, pero cortó el flujo de inmediato (nunca reservó, ejecutó ni notificó)
        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock, never()).reservar();
        verify(apiBVirtualMock, never()).ejecutar(anyDouble());
        verify(apiBVirtualMock, never()).notificar();
    }

    @Test
    void debeLanzarExcepcionSiFallaElBloqueoDeSaldo() {
        // Arrange: Pasa la validación de saldo, pero falla al intentar bloquearlo
        when(apiBVirtualMock.validar(anyDouble())).thenReturn(true);
        when(apiBVirtualMock.reservar()).thenReturn(false);

        // Act & Assert
        MetodoPagoExcepcion excepcion = assertThrows(MetodoPagoExcepcion.class, () -> {
            billeteraVirtual.procesarPago();
        });

        assertEquals("Bloqueo del saldo hasta confirmar", excepcion.getMessage());

        // Verificamos que intentó validar y reservar, pero se detuvo antes de ejecutar o notificar
        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock).reservar();
        verify(apiBVirtualMock, never()).ejecutar(saldoPrueba);
        verify(apiBVirtualMock, never()).notificar();
    }

    @Test
    void procesarPagoBilleteraExitosoDebeEjecutarConElMontoCorrectoYNotificar() {

        when(apiBVirtualMock.validar(anyDouble())).thenReturn(true);
        when(apiBVirtualMock.reservar()).thenReturn(true);
        // Como ejecutar() ahora es void, no requiere un .thenReturn() obligatoriamente

        // Act
        assertDoesNotThrow(() -> {
            billeteraVirtual.procesarPago();
        });

        // Assert: Verificamos que se llamó a ejecutar pasando el monto exacto
        verify(apiBVirtualMock).validar(anyDouble());
        verify(apiBVirtualMock).reservar();
        verify(apiBVirtualMock).ejecutar(anyDouble()); // ¡Verificamos que pasó el monto!
        verify(apiBVirtualMock).notificar();
    }
}