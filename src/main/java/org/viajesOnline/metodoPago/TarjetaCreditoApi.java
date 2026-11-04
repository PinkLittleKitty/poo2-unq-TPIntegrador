package org.viajesOnline.metodoPago;

import java.time.LocalDate;

public interface ApiTarjetaCredito {

    public boolean validar(String nro_tarjeta,String cvv,LocalDate vencimiento);

    public void reservar();

    public void ejecutar();

}
