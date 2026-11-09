package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;

public class CostoTrasladoStandard implements Traslado {

    private PlataformaTransporte plataforma;
    private DireccionMock direccionDestino;
    private int cantPasajeros;

    public CostoTrasladoStandard(int cantPasajeros, PlataformaTransporte plataforma, DireccionMock direccionDestino) {

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
