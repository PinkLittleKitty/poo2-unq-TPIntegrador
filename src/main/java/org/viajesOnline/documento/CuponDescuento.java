package org.viajesOnline.documento;

public class CuponDescuento implements FormatearComprobante {
    private double porcentajeDescuento;

    public CuponDescuento(double porcentajeDescuento)
    {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double porcentajeDescuento() {
        return porcentajeDescuento;
    }

    public String obtenerArchivoFormateado(FormatoVisitanteDocumento v){return v.obtenerArchivo(this);}
}
