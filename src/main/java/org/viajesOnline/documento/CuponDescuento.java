package org.viajesOnline.documento;

import java.util.UUID;

public class CuponDescuento extends Documento implements FormatearComprobante {
    private double porcentajeDescuento;

    public CuponDescuento(String codTrans, double porcentajeDescuento)
    {
        super(codTrans, porcentajeDescuento);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double porcentajeDescuento() {
        return porcentajeDescuento;
    }

    public String obtenerArchivoFormateado(FormatoVisitanteDocumento v){return v.obtenerArchivo(this);}
}
