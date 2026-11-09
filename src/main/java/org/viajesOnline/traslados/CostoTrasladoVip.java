package org.viajesOnline.traslados;

public class CostoTrasladoVip implements Traslado {

    private float cargoBase;
    private float porcentajeFijo;

    public CostoTrasladoVip(float cargoBase, float porcentajeFijo){

        this.porcentajeFijo = porcentajeFijo;
        this.cargoBase = cargoBase;
    }

    private float porcentajeFijo() {
        return 1 - porcentajeFijo / 100;
    }

    @Override
    public float calcularCosto(float precio){

        return (precio * this.porcentajeFijo()) + cargoBase;
    }
}
