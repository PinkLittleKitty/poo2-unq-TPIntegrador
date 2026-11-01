package org.viajesOnline.traslados;

public class Punto_encuentro implements CostoTraslado {

    //la estimacion de disponibilidad depende de si hay guias en el puesto

    @Override
    public double calcularCosto(ContextoTrasladoAeropuertoHotel c){

        //# Donde dice "El costo se calcula como porcentaje fijo
        // sobre el valor total de la reserva" (relacionar esto con
        // precio total de los servicios/paquetes turísticos)
        return 0;
    }
}
