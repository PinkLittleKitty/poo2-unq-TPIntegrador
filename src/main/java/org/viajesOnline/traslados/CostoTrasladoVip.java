package org.viajesOnline.traslados;

public class CostoTrasladoVip implements CostoTraslado {

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
    public float calcularCosto(ContextoTraslado c){

       //# Donde dice "El costo se calcula como porcentaje fijo sobre el valor total de la reserva"
        // (relacionar esto con precio total de los servicios/paquetes turísticos)
        return (c.precioFinalReserva() * this.porcentajeFijo()) + cargoBase;
    }
}
