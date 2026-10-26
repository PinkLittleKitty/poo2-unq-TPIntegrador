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

ContextoReserva --> "*" Catalogo : servicios 
%% Este sería nuestro cliente, y este mismo usaría ServicioTraslado para costear el transporte

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
- generarVoucher()
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

Traslado -- Adaptador
Adaptador --> TrasladoEstandar
Adaptador --> TrasladoVip
Adaptador --> SinTraslado

class Traslado {
    costo()
}

class Adaptador {
    costo()
}

class TrasladoEstandar {
    estimarTraslado(int cantidadPasajeros, Direccion direccionDestino)
}

class TrasladoVip {
    # Donde dice "El costo se calcula como porcentaje fijo sobre el valor total de la reserva" (relacionar esto con precio total de los servicios/paquetes turísticos)
    calcularCosto(float precio)
}

class SinTraslado {
    costo() # Qué onda con la estimación?
}

MetodoDePago <|-- TarjetaCreditoInteracional
MetodoDePago <|-- TransferenciaBancaria
MetodoDePago <|-- BilleteraVirtual



class MetodoDePago {
    <<Abstract>>
    ProcesarPago()* # Este método tendría los cuatro en orden, hook, no lo implementan los otros
    validarDatos()*
    reservarFondos()*
    ejecutarTransaccion()*
    notificarResultado()
}

class TarjetaCreditoInteracional {
    - nroTarjeta
    - cvv
    - vencimiento
    validarDatos()
    reservarFondos()
    ejecutarTransaccion()
    notificarResultado()
}

class TransferenciaBancaria {
    - cbu
    - alias    
    validarDatos()
    reservarFondos()
    ejecutarTransaccion()
    notificarResultado()
}

class BilleteraVirtual {
    - saldo
    validarDatos()
    reservarFondos()
    ejecutarTransaccion()
    notificarResultado()
}

ObservadorAbstracto <|-- NotificadorMail
ObservadorAbstracto <|-- GeneradorFactura
ObservadorAbstracto <|-- Fidelización

class ObservadorAbstracto {
    <<Abstract>>
    actualizar(Reserva)*
}

class NotificadorMail {
    actualizar(Reserva) # La reserva puede estar CONFIRMADA, EN_CURSO y FINALIZADA.
    # persona.enviarMail(String direccionDestino, String titulo, String mensaje, Object adjunto)
}

class GeneradorFactura {
    actualizar(Reserva)
}

class Fidelización {
    actualizar(Reserva)
}
