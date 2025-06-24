/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author AdielS
 */
public class PaqueteTuristico {
    private String nombrePaquete;
    private String destino;
    private double precioDestino;
    private String hospedaje;
    private String transportePlaca;
    private String actividades;
    private int duracionDias;
    private String fechaInicio;
    private String fechaFin;
    private double precioTotal;

    public PaqueteTuristico() {}

    public PaqueteTuristico(String nombrePaquete, String destino, double precioDestino, String hospedaje,
                            String transportePlaca, String actividades, int duracionDias,
                            String fechaInicio, String fechaFin, double precioTotal) {
        this.nombrePaquete = nombrePaquete;
        this.destino = destino;
        this.precioDestino = precioDestino;
        this.hospedaje = hospedaje;
        this.transportePlaca = transportePlaca;
        this.actividades = actividades;
        this.duracionDias = duracionDias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecioDestino() {
        return precioDestino;
    }

    public void setPrecioDestino(double precioDestino) {
        this.precioDestino = precioDestino;
    }

    public String getHospedaje() {
        return hospedaje;
    }

    public void setHospedaje(String hospedaje) {
        this.hospedaje = hospedaje;
    }

    public String getTransportePlaca() {
        return transportePlaca;
    }

    public void setTransportePlaca(String transportePlaca) {
        this.transportePlaca = transportePlaca;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
