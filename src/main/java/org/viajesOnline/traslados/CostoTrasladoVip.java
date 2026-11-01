package org.viajesOnline.traslados;

public class CostoTrasladoVip implements CostoTraslado {

    private double cargoBase;
    private double porcentajeFijo;

    public CostoTrasladoVip(double cargoBase, double porcentajeFijo){

        this.porcentajeFijo = porcentajeFijo;
        this.cargoBase = cargoBase;
    }

    private double porcentajeFijo() {return porcentajeFijo / 100;}

    @Override
    public double calcularCosto(ContextoTrasladoAeropuertoHotel c){

       //# Donde dice "El costo se calcula como porcentaje fijo sobre el valor total de la reserva"
        // (relacionar esto con precio total de los servicios/paquetes turísticos)
        return c.precioFinalReserva() * this.porcentajeFijo() + cargoBase;
    }
}
