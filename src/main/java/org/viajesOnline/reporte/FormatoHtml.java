package org.viajesOnline.reporte;

class FormatoHtml implements FormatoVisitanteReportes {

    private String reporteHtml;

    public void visitar(ServicioMasReservado s){

        //guarda la informacion para exportar en formato html
        //reporteHtml = e.pideCosasALaClase
    }

    public void visitar(RegistroDeCupos r){

        //guarda la informacion para exportar en formato html
        //reporteHtml = e.pideCosasALaClase
    }

    public void visitar(RegistroDeOperadores o){

        //guarda la informacion para exportar en formato html
        //reporteHtml = e.pideCosasALaClase
    }

    public String obtenerReporte(){

        //retorna el reporte en html
        return reporteHtml;
    }
}

