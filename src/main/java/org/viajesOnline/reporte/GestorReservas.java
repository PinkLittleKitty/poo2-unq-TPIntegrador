package org.viajesOnline.reporte;

import org.viajesOnline.reservas.Reserva;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
  private final List<Reserva> reservas = new ArrayList<>();
  private static GestorReservas instance;

  private GestorReservas() {} // Private constructor

  public static GestorReservas getInstance() {
    if (instance == null) {
      instance = new GestorReservas();
    }
    return instance;
  }

  public void addReserva(Reserva r) {
    reservas.add(r);
  }

  public void addReserva(List<Reserva> r) {
    reservas.addAll(r);
  }

  public void delReserva(Reserva r) {
    reservas.remove(r);
  }

  public List<Reserva> getReservas() {
    return List.copyOf(reservas);
  }

  void borrarTodo(){
    reservas.clear();
  }
}
