package org.viajesOnline.traslados;

public class PuntoEncuentro implements Traslado{

    private boolean hayGuiasDisponibles = true;
    private PuntoEncuentro puntoEncuentro;
    private float precioEnPuntoEncuentro;


    public PuntoEncuentro(PuntoEncuentro puntoEncuentro){

        this.setPuntoEncuentro(puntoEncuentro);
    }

    public void setPrecioEnPuntoEncuentro(float nuevoPrecio){nuevoPrecio = precioEnPuntoEncuentro;}

    public void setPuntoEncuentro(PuntoEncuentro puntoEncuentro) {

        this.puntoEncuentro = puntoEncuentro;
    }

    public void guiasDisponibles(boolean guiasDisponibles) {

        this.hayGuiasDisponibles = guiasDisponibles;
    }

    private void hayGuiasDisponibles() {

        if (!hayGuiasDisponibles) {
            throw new UnsupportedOperationException("No hay guías disponibles");
        }
    }

    @Override
    public float calcularCosto(ContextoTraslado c){return precioEnPuntoEncuentro;}

}