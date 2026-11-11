package org.viajesOnline.traslados;

public class PuntoEncuentro implements Traslado{

    private boolean hayGuiasDisponibles = true;
    private LugarPuntoEncuentroMock puntoEncuentro;
    private final float precioEnPuntoEncuentro = 0;


    public PuntoEncuentro(LugarPuntoEncuentroMock puntoEncuentro, boolean guiasDisponibles){

        this.setPuntoEncuentro(puntoEncuentro);
        this.setGuiasDisponibles(guiasDisponibles);
    }

    public void setPuntoEncuentro(LugarPuntoEncuentroMock puntoEncuentro) {

        this.puntoEncuentro = puntoEncuentro;
    }

    public LugarPuntoEncuentroMock puntoEncuentro() {return puntoEncuentro;}

    public void setGuiasDisponibles(boolean guiasDisponibles) {

        this.hayGuiasDisponibles = guiasDisponibles;
    }

    public boolean hayGuiasDisponibles() {

        if (!hayGuiasDisponibles) {
            throw new UnsupportedOperationException("No hay guías disponibles");
        }

        return true;
    }

    public float precioEnPuntoEncuentro(){return precioEnPuntoEncuentro;}

    @Override
    public float calcularCosto(ContextoTraslado c){return this.precioEnPuntoEncuentro();}

}