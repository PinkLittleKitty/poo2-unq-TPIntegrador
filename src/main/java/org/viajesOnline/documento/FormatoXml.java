package org.viajesOnline.documento;


public class FormatoXml implements FormatoVisitanteDocumento {

    private String archivoXml;

    public String obtenerArchivo(ComprobanteFiscal c){

            // Se genera el XML y se asigna al atributo de la clase
            this.archivoXml = """
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
                            c.emisor(),
                            c.fechaEmision(),
                            c.codTransaccion(),
                            //c.numeroTarjeta(),
                            //c.codigoAutorizacion(),
                            c.monto()
            );

            return archivoXml;
        }
}

