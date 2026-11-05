package org.viajesOnline.metodoPago;

import java.time.YearMonth;

public interface ApiTransferenciaBancaria {

    public boolean validar(String Cbu, String aliasUsuario);

    public boolean reservar();

    public double ejecutar();
}
