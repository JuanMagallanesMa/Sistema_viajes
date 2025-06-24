
package entidades;

import java.sql.Timestamp;

/**
 *
 * @author MICHELLE
 */
public class Factura {
 public int reserva_id,  id;
 public String  metodo, estado;
 public Double subTotal, iva, total;
 public java.sql.Timestamp fecha_emision;

    public Factura() {
    }

    public Factura(int reserva_id, int id, String metodo, String estado, Double subTotal, Double iva, Double total, Timestamp fecha_emision) {
        this.reserva_id = reserva_id;
        this.id = id;
        this.metodo = metodo;
        this.estado = estado;
        
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.fecha_emision = fecha_emision;
    }

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Timestamp getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Timestamp fecha_emision) {
        this.fecha_emision = fecha_emision;
    }
 
 
}
