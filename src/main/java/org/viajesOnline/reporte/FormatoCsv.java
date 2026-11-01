package org.viajesOnline.reporte;

class FormatoCsv implements FormatoVisitante {

    private String reporteCsv;

    public void visitar(ServicioMasReservado s){

        //guarda la informacion para exportar en formato csv
        //reporteCsv= e.pideCosasALaClase
    }

    public void visitar(RegistroDeCupos r){

        //guarda la informacion para exportar en formato csv
        //reporteCsv = e.pideCosasALaClase
    }

    public void visitar(RegistroDeOperadores o){

        //guarda la informacion para exportar en formato csv
        //reporteCsv = e.pideCosasALaClase
    }

    public String obtenerReporte(){

        //retorna para exportar en formato csv
        return reporteCsv;
    }
}
