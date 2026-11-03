package org.viajesOnline.notificacionReservas;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.estados.EstadoReserva;

import java.util.ArrayList;
import java.util.List;


//finalizada tiene [notificador mail, generador voucher]
//confirmada [notificador mail]
//en_curso [notificador mail]
//cancelar [club viajeros]
//PERO HAY ALGUNOS ESTADOS QUE ESTAN VACIOS. QUE SUCEDE AHI?
public abstract class SujetoEstadoAbstracto {

    private List<ObservadorNotificadorAbstracto> notificadores = new ArrayList<>();

    //ESTO QUIZAS VUELE
    public SujetoEstadoAbstracto(){};

    public void agregarNotificador(ObservadorNotificadorAbstracto n){notificadores.add(n);}

    public void quitarNotificador(ObservadorNotificadorAbstracto n){notificadores.remove(n);}

    public void notificarObservadores(ContextoReserva c){

        for(ObservadorNotificadorAbstracto n : notificadores){

            n.recibirNotificacion(c);
        }
    }

}
