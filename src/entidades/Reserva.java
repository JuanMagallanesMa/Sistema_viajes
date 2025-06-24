
package entidades;

import java.sql.Timestamp;

/**
 *
 * @author MICHELLE
 */
public class Reserva {
public int cliente_id,  id;
 public String  destino, origen, clase, estado, nombres_apellidos;
 public Double cantidad_pasajeros, precio_total;
 public java.sql.Timestamp fecha_reserva, fecha_viaje;

    public Reserva() {
    }

    public Reserva(int id, int cliente_id, String destino, String origen, String clase, String estado, Double cantidad_pasajeros, Double precio_total, Timestamp fecha_reserva, Timestamp fecha_viaje, String nombres_apellidos) {
       this.id = id;
        this.cliente_id = cliente_id;
        this.destino = destino;
        this.origen = origen;
        this.clase = clase;
        this.estado = estado;
        this.cantidad_pasajeros = cantidad_pasajeros;
        this.precio_total = precio_total;
        this.fecha_reserva = fecha_reserva;
        this.fecha_viaje = fecha_viaje;
        this.nombres_apellidos = nombres_apellidos;
    }

      public int getId() {
        return id;
    }
 public String getNombres_apellidos() {
        return nombres_apellidos;
    }

    public void setNombres_apellidos(String nombres_apellidos) {
        this.nombres_apellidos = nombres_apellidos;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCantidad_pasajeros() {
        return cantidad_pasajeros;
    }

    public void setCantidad_pasajeros(Double cantidad_pasajeros) {
        this.cantidad_pasajeros = cantidad_pasajeros;
    }

    public Double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(Double precio_total) {
        this.precio_total = precio_total;
    }

    public Timestamp getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Timestamp fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Timestamp getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(Timestamp fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }
}
