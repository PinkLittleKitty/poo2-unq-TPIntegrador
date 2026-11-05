package org.viajesOnline.gestorDeRecibos;


public class ComprobanteCbu extends Recibo {

    private String aliasUsuario;
    private String codTransaccion;
    private String cbu;

    public ComprobanteCbu(String codTransaccion,double monto,String Cbu,String aliasUsuario)
    {
        super(codTransaccion,monto);
        this.aliasUsuario = aliasUsuario;
        this.codTransaccion = codTransaccion;
        this.cbu = Cbu;
    }

    public String obtenerAliasUsuario(){return aliasUsuario;}

    public String obtenerCbu(){return cbu;}

    @Override
    public void mostrarDetalles() {
        // Reutilizamos el comportamiento de la clase padre para no repetir código
        super.mostrarDetalles();
        System.out.println("Alias Usuario: " + aliasUsuario);
        System.out.println("Cbu: $" + cbu);
    }
}


