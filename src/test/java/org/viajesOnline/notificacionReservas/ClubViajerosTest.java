package org.viajesOnline.notificacionReservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class ClubViajerosTest {

    private MailSender mailSenderMock;
    private ClubViajeros clubViajeros;
    private ContextoReserva reservaMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {

        mailSenderMock = mock(MailSender.class);
        //clubViajeros = new ClubViajeros(mailSenderMock);
        reservaMock = mock(ContextoReserva.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void reservaCancelada() {
        assertDoesNotThrow(() -> clubViajeros.reservaCancelada(reservaMock));
    }

    @Test
    void eventosNoSuscritos() {
        assertDoesNotThrow(() -> {
            clubViajeros.reservaPendiente(reservaMock);
            clubViajeros.reservaConfirmada(reservaMock);
            clubViajeros.reservaEnCheckin(reservaMock);
            clubViajeros.reservaEnCurso(reservaMock);
            clubViajeros.reservaFinalizada(gestor, reservaMock);
        });
        verifyNoInteractions(reservaMock);
    }
}

