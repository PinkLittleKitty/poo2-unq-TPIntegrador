package org.viajesOnline.documentoTest;

import org.junit.jupiter.api.BeforeEach;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.metodoPago.ApiTarjetaCredito;
import org.viajesOnline.metodoPago.MetodoPagoTarjetaInternacional;

import java.time.YearMonth;

import static org.mockito.Mockito.mock;

public class VoucherPagoTest {

    private ApiTarjetaCredito visaInt;
    private ApiTarjetaCredito mastercardInt;

    private MetodoPagoTarjetaInternacional tarjetaInternacional;
    private MetodoPagoTarjetaInternacional otraTarjetaInternacional;

    private GestorDeDocumentos gestorMock;
    private Documento voucherParaPago;

    @BeforeEach
    void setUp() {

        voucherParaPago = mock(Documento.class);
        gestorMock = mock(GestorDeDocumentos.class);
        visaInt = mock(ApiTarjetaCredito.class);
        mastercardInt = mock(ApiTarjetaCredito.class);

        tarjetaInternacional = new MetodoPagoTarjetaInternacional(gestorMock,500.2,"1234567891234567", "111",
                YearMonth.of(2020, 12), visaInt);

        otraTarjetaInternacional = new MetodoPagoTarjetaInternacional(gestorMock,500.2,"1234567891111561", "555",
                YearMonth.of(2030, 12), mastercardInt);

    }




}
