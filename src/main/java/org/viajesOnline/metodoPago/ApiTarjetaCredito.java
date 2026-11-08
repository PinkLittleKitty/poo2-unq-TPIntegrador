package org.viajesOnline.metodoPago;
import java.time.YearMonth;
import java.time.LocalDate;

public interface ApiTarjetaCredito {

    public boolean validar(String nro_tarjeta,String cvv,YearMonth vencimiento);

    public boolean reservar();

    public double ejecutar();

}
