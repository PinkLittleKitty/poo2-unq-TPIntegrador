package org.viajesOnline.traslados;

public class PuntoEncuentro implements Traslado{

    private boolean hayGuiasDisponibles = true;
    private LugarPuntoEncuentro puntoEncuentro;
    private final float precioEnPuntoEncuentro = 0;


    public PuntoEncuentro(LugarPuntoEncuentro puntoEncuentro,boolean guiasDisponibles){

        this.setPuntoEncuentro(puntoEncuentro);
        this.setGuiasDisponibles(guiasDisponibles);
    }

    public void setPuntoEncuentro(LugarPuntoEncuentro puntoEncuentro) {

        this.puntoEncuentro = puntoEncuentro;
    }

    public LugarPuntoEncuentro puntoEncuentro() {return puntoEncuentro;}

    public void setGuiasDisponibles(boolean guiasDisponibles) {

        this.hayGuiasDisponibles = guiasDisponibles;
    }

    private void hayGuiasDisponibles() {

        if (!hayGuiasDisponibles) {
            throw new UnsupportedOperationException("No hay guías disponibles");
        }
    }

    public float precioEnPuntoEncuentro(){return precioEnPuntoEncuentro;}

    @Override
    public float calcularCosto(ContextoTraslado c){return this.precioEnPuntoEncuentro();}

}