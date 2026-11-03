package org.viajesOnline.reporte;


class FormatoTextoPlano implements FormatoVisitante{

    private String reporteTextoPlano;

    public void visitar(ServicioMasReservado s){

        //guarda la informacion para exportar en formato
        //reporteTextoPlano = s.pideCosasALaClase
    }

    public void visitar(RegistroDeCupos r){

        //guarda la informacion para exportar en formato texto plano
        //reporteTextoPlano = r.pideCosasALaClase
    }

    public void visitar(RegistroDeOperadores o){

        //guarda la informacion para exportar en formato texto plano
        //reporteTextoPlano = o.pideCosasALaClase
    }

    public String obtenerReporte(){

        //retorna reporte texto plano
        return reporteTextoPlano;
    }
}
