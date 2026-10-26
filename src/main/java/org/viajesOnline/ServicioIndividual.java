package org.viajesOnline;

public class ServicioIndividual extends Catalogo {
  private int idReserva;
  private String proovedor;
  private Categoria categoria;

  public String proovedor(){
    return proovedor;
  }

  public Categoria categoria(){
    return categoria;
  }

  public Boolean esValido(){
      return true;
  }

  public void agregarAtributo(Atributo _atributo){

  }
  public ServicioIndividual(int idReserva, String proveedor, double precio, Categoria categoria, String nombre, String descripcion, int cupo){
    super(nombre,descripcion,precio,cupo);
    this.idReserva = idReserva;
    this.proovedor = proveedor;
    this.categoria = categoria;
  }

}
