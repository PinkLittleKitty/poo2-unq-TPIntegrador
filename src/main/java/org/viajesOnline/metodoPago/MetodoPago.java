package org.viajesOnline.metodoPago;

/*Lo de interfaces se puede referir al protocolo que tiene cada una de esas clases
      que van a definir para la estructura del template method. Como menciona el enunciado
      no es necesario que los mensajes de esas clases tengan un metodo/implementacion solo
      que modelen el disparador de estos eventos. Va de la mano con lo que te escribi antes,
      podes usar un mock para comprobar que se disparan los eventos.
    //# TEMPLATE METHOD Este método tendría los cuatro en orden, hook, no lo implementan los otros
     */

public abstract class MetodoPago {

    public final void procesarPago(){

        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract boolean validarDatos();

    public abstract boolean reservarFondos();

    public abstract double ejecutarTransaccion();

    public void notificarResultado(){

        //implementacion registro de codigo de transaccion
    }
}

