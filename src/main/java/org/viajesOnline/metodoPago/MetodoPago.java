package org.viajesOnline.metodoPago;

public abstract class MetodoPago {

    //# TEMPLATE METHOD Este método tendría los cuatro en orden, hook, no lo implementan los otros
    public final void ProcesarPago(){

        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    /*Lo de interfaces se puede referir al protocolo que tiene cada una de esas clases
      que van a definir para la estructura del template method. Como menciona el enunciado
      no es necesario que los mensajes de esas clases tengan un metodo/implementacion solo
      que modelen el disparador de estos eventos. Va de la mano con lo que te escribi antes,
      podes usar un mock para comprobar que se disparan los eventos.
     */

    public abstract boolean validarDatos();

    public abstract void reservarFondos();

    public abstract void ejecutarTransaccion();

    public void notificarResultado(){};
}

