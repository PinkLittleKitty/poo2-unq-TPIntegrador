package org.viajesOnline.documento;


public interface FormatoVisitanteDocumento {

    public String obtenerArchivo(ComprobanteFiscal c);

    public String obtenerArchivo(CuponDescuento c);
}
