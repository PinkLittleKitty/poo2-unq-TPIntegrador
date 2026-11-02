package org.viajesOnline.traslados;

import org.viajesOnline.utils.PlataformaTransporte;

public class CostoTrasladoStandard implements CostoTraslado {

    private PlataformaTransporte plataforma;

    public CostoTrasladoStandard(PlataformaTransporte plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public float calcularCosto(ContextoTraslado c) {
        return plataforma.estimarTraslado(c.cantPasajeros(), c.direccionDestino());
    }

}
