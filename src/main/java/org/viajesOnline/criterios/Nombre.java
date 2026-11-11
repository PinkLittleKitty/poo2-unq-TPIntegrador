package org.viajesOnline.criterios;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.Catalogo;
import org.viajesOnline.exceptions.CompletarCampos;

import java.util.Objects;

public class Nombre implements Criterio{
  private String nombre;

  private static void verificacion(String n){
    try{
      Objects.requireNonNull(n);
      Validate.notBlank(n);
    } catch (Exception e) {
      throw new CompletarCampos("Completar el campo");
    }
  }

  public Nombre(String nombre) {
    verificacion(nombre);
    this.nombre = nombre;
  }

  @Override
  public boolean satisfaceCriterio(Catalogo cat) {
    return this.getNombre().equalsIgnoreCase(cat.getNombre());
  }

  public String getNombre() {
    return nombre;
  }
}
