package org.viajesOnline.traslados;

import org.viajesOnline.traslados.PlataformaTransporte;
import org.viajesOnline.traslados.DireccionMock;

public class CostoTrasladoStandard implements CostoTraslado {

    private PlataformaTransporte plataforma;
    private DireccionMock direccionDestino;

    public CostoTrasladoStandard(PlataformaTransporte plataforma,DireccionMock direccionDestino) {
        this.plataforma = plataforma;
        this.direccionDestino = direccionDestino;
    }

    @Override
    public float calcularCosto(ContextoTraslado c) {
        return plataforma.estimarTraslado(c.cantPasajeros(),direccionDestino);
    }

}
