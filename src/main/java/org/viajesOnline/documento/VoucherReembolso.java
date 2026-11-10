package org.viajesOnline.documento;


public class VoucherReembolso extends Documento{

    private String nombreVoucher;
    private double costoServicio;
    private double tasasAdministrativas;

    public VoucherReembolso(String codTransaccion,double costoServicio, double tasasAdministrativas)
    {
        super(codTransaccion);
        this.costoServicio = costoServicio;
        this.tasasAdministrativas = tasasAdministrativas;
        this.nombreVoucher = "Voucher Reembolso";
    }

    public String tipoDocumento(){return nombreVoucher;}

    public double obtenerCostoServicio(){return costoServicio;}

    public double obtenerTasasAdministrativas(){return tasasAdministrativas;}

    @Override
    public void mostrarDetalles() {
        // Reutilizamos el comportamiento de la clase padre para no repetir código
        super.mostrarDetalles();
        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Costo Por Servicio: " + this.obtenerCostoServicio());
        System.out.println("Tasas Administrativas: " + this.obtenerTasasAdministrativas());
    }


}


