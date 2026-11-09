package org.viajesOnline.traslados;

public class TrasladoVip {

    private float cargoBase;
    private float porcentajeFijo;

    public TrasladoVip(float cargoBase, float porcentajeFijo){

        this.porcentajeFijo = porcentajeFijo;
        this.cargoBase = cargoBase;
    }

    private float porcentajeFijo() {
        return 1 - porcentajeFijo / 100;
    }


    public float calcularCosto(float precio){

        return (precio * this.porcentajeFijo()) + cargoBase;
    }
}
