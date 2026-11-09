package org.viajesOnline.traslados;

public class TrasladoStandard implements Traslado {

    private PlataformaTransporte plataforma;
    private DireccionMock direccionDestino;
    private int cantPasajeros;

    public TrasladoStandard(int cantPasajeros, PlataformaTransporte plataforma, DireccionMock direccionDestino) {

        this.setCantPasajeros(cantPasajeros);
        this.plataforma = plataforma;
        this.direccionDestino = direccionDestino;
    }

    public void setCantPasajeros(int cantPasajeros) {

        this.cantPasajeros = cantPasajeros;
    }

    @Override
    public float calcularCosto() {

        return plataforma.estimarTraslado(cantPasajeros,direccionDestino);
    }

}
