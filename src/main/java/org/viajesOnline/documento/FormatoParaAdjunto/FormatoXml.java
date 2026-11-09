package org.viajesOnline.documento.FormatoParaAdjunto;


import org.viajesOnline.documento.Documento;

public class FormatoXml extends FormatoAdjunto {

    private String archivoXml;

    public String obtenerArchivo(Documento c) {
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
        );

        return archivoXml;
    }

    /*
    public String obtenerArchivo(CuponDescuento c){

            // Se genera el XML y se asigna al atributo de la clase
            this.archivoXml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <voucherPago>
                    <descuento>%s</descuento>
                </voucherPago>
                """.formatted(
                            c.porcentajeDescuento()
            );

            return archivoXml;
        }
    */
}


