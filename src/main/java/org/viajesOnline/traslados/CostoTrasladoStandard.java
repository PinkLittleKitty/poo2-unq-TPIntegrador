package org.viajesOnline.traslados;

import org.viajesOnline.utils.PlataformaTransporte;

public class CostoTrasladoStandard implements CostoTraslado {

    private PlataformaTransporte plataforma; //costo estandar

    @Override
    public float calcularCosto(ContextoTraslado c) {

        //interpreto que plataforma la conoce desde siempre
        return plataforma.estimarTraslado(c.cantPasajeros(), c.direccionDestino());
    }

}
