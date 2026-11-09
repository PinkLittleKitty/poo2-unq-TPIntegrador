package org.viajesOnline.traslados;

public class CostoTrasladoStandard implements Traslado {

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
