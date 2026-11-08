package org.viajesOnline.documento;

public class ComprobanteCbu extends Documento {

    private String aliasUsuario;
    private String cbu;
    private String nombreComprobante;
    protected double monto;

    public ComprobanteCbu(String codTransaccion,double monto,String cbu,String aliasUsuario)
    {
        super(codTransaccion);
        this.monto = monto;
        this.aliasUsuario = aliasUsuario;
        this.cbu = cbu;
        this.nombreComprobante = "Comprobante Cbu";
    }

    public double monto() { return monto; }

    public String obtenerAliasUsuario(){return aliasUsuario;}

    public String obtenerCbu(){return cbu;}

    public String tipoDocumento(){return nombreComprobante;}

    @Override
    public void mostrarDetalles() {
        super.mostrarDetalles();
        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Alias Usuario: " + this.obtenerAliasUsuario());
        System.out.println("Cbu: " + this.obtenerCbu());
    }
}


