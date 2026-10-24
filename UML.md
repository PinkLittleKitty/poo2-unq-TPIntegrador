classDiagram
Catalogo "*" <-- "servicios" Cliente
Catalogo <|-- ServicioIndividual
Catalogo <|-- PaqueteTurístico
PaqueteTurístico "Catalogos" o-- "*" Catalogo

ServicioIndividual --> "1" Categoria : Categoria
ServicioIndividual *-- "*" DefinicionAtributo : atributosDinamicos
DefinicionAtributo --> "1" TipoDato : validaCon

TipoDato <|-- TipoTexto
TipoDato <|-- TipoNumerico
TipoDato <|-- TipoBooleano
TipoDato <|-- TipoFecha

EstadoReserva <--o ContextoReserva
EstadoReserva <|-- SolicitudPendiente
EstadoReserva <|-- Confirmada
EstadoReserva <|-- EnCheckin
EstadoReserva <|-- EnCurso
EstadoReserva <|-- Finalizada
EstadoReserva <|-- Cancelada

SolicitudPendiente --> Confirmada : Siguiente
Confirmada --> EnCheckin : Siguiente
EnCheckin --> EnCurso : Siguiente
EnCurso --> Finalizada : Siguiente

class Cliente {
%% Es conceptual
+ cliente(List<Catalogo>) cliente
+ navegarCatalogo() void
  }

class Catalogo {
<<Abstract>>
- nombre : String
- descripcion : String
- precioBase : real
- cupoDisponible : int
+ agregarCupo(num) : int %% necesitamos decir cuantos cupos reservo?
+ liberarCupo(num) : int
+ getCupo() : int
+ precioBase()* real
+ precioFinal(descuento)* real
+ catalogo(nombre, descripcion) Catalogo
  }

class ServicioIndividual {
- ID : int
- proovedor : String
- precioBase : real
+ precioBase() real
+ precioFinal(descuento) real
+ proovedor() String
+ categoria() Categoria
+ esValido() boolean
+ agregarAtributo(atributo : DefinicionAtributo) void
+ servicioIndividual(precio, nombre, descripcion) ServicioIndividual
  }

class PaqueteTurístico {
- descuento : real
+ PaqueteTurístico(List<Catalogo>, descuento, nombre, descripcion)
+ agregarCupo(num) : int %% aca tenemos que reescribirla logica para la lista interna del paquete.
+ liberarCupo(num) : int
+ porcentaje()
+ setDescuento()
+ precioBase() real
+ precioFinal(descuento) real
+ AddCatalogo()
+ RemoveCatalogo()
+ GetCatalogo()
  }

class Categoria {
<<Enumeration>>
VUELO
EXCURSION
ALOJAMIENTO
ALQUILER_DE_AUTOS
}

class DefinicionAtributo {
- nombre : String
- esObligatorio : boolean
- valor : Object
+ validar() boolean
+ esObligatorio() boolean
+ getNombre() String
  }

class TipoDato {
<<interface>>
+ esValido(valor : Object)
  }

class TipoTexto {
+ esValido(valor : Object)
  }

class TipoNumerico {
+ esValido(valor : Object)
  }

class TipoBooleano {
+ esValido(valor : Object)
  }

class TipoFecha {
+ esValido(valor : Object)
  }

class ContextoReserva {
- estadoActual : EstadoReserva
- servicios : List<Catalogo>

+ getEstadoActual() EstadoReserva
+ setEstado(EstadosReserva) void
+ siguiente() void
+ cancelar() void
+ agregarServicio(Catalogo) void
+ quitarServicio(Catalogo) void
+ liberarCupos() void %% no deberia ser para todos
+ decrementarCupos() void %% stream que recorra -servicios- y llame liberarCupo(serv.getCupo())
+ solicitarReembolso() void %% delega a otra cosa de pagos
- verificacionAgregarQuitarServicio()
- otrasVerificaciones()
  }

class EstadoReserva {
<<abstract>>
siguiente() EstadoReserva
cancelar() EstadoReserva
}

class SolicitudPendiente {
siguiente() EstadoReserva
cancelar() EstadoReserva
}

class Confirmada {
siguiente() EstadoReserva
cancelar() EstadoReserva
}

class EnCheckin {
siguiente() EstadoReserva
cancelar() EstadoReserva
}

class EnCurso {
siguiente() EstadoReserva
cancelar() EstadoReserva
}

class Finalizada {
siguiente() EstadoReserva
cancelar() EstadoReserva
}

class Cancelada {
siguiente() EstadoReserva
cancelar() EstadoReserva
}
