package org.viajesOnline.gestorDeRecibos;

public class FormatoXml implements FormatoVisitante{

    private String contenidoXml;

    @Override
    public void visitar(VoucherParaPago v){

            // Se genera el XML y se asigna al atributo de la clase
            this.contenidoXml = """
        <?xml version="1.0" encoding="UTF-8"?>
        <voucherPago>
            <emisor>%s</emisor>
            <fechaEmision>%s</fechaEmision>
            <codigoTransaccion>%s</codigoTransaccion>
            <numeroTarjeta>%s</numeroTarjeta>
            <codigoAutorizacion>%s</codigoAutorizacion>
            <montoTotal>%.2f</montoTotal>
        </voucherPago>
        """.formatted(
                    v.emisor(),
                    v.fechaEmision(),
                    v.codTransaccion(),
                    v.numeroTarjeta(),
                    v.codigoAutorizacion(),
                    v.monto()
            );
        }

        public String obtenerArchivoConFormato() {
            return this.contenidoXml;
        }

}
