package org.viajesOnline.documento;

public class ComprobanteCbu extends Documento {

    private String aliasUsuario;
    private String cbu;
    private String nombreComprobante;

    public ComprobanteCbu(String codTransaccion,double monto,String cbu,String aliasUsuario)
    {
        super(codTransaccion,monto);
        this.aliasUsuario = aliasUsuario;
        this.cbu = cbu;
        this.nombreComprobante = "Comprobante Cbu";
    }

    public String obtenerAliasUsuario(){return aliasUsuario;}

    public String obtenerCbu(){return cbu;}

    public String tipoDocumento(){return nombreComprobante;}

    @Override
    public void mostrarDetalles() {
        // Reutilizamos el comportamiento de la clase padre para no repetir código
        super.mostrarDetalles();
        System.out.println("Alias Usuario: " + aliasUsuario);
        System.out.println("Cbu: " + cbu);
    }
}


