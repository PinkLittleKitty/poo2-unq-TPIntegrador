package org.viajesOnline.traslados;

public class TrasladoVip {

    private final float cargoBase;
    private final float porcentajeFijo;

    public TrasladoVip(float cargoBase, float porcentajeFijo){

        this.porcentajeFijo = porcentajeFijo;
        this.cargoBase = cargoBase;
    }

    public float cargoBase() {return cargoBase;}

    public float porcentajeFijo() {
        return 1 - porcentajeFijo / 100;
    }

    public float calcularCosto(float precio){

        return (precio * this.porcentajeFijo()) + cargoBase;
    }
}
