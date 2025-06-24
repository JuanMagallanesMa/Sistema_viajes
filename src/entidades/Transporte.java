/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Asus
 */
public class Transporte {
    
    private String placa;
    private String modeloVehiculo;
    private String tipoVehiculo;
    private int kilometraje;
    private int anioFabricacion;
    private String consumoCombustiblePorKm;
    private boolean estado;

    public Transporte() {}

    public Transporte(String placa, String modeloVehiculo, String tipoVehiculo, int kilometraje, int anioFabricacion, String consumoCombustiblePorKm, boolean estado) {
        this.placa = placa;
        this.modeloVehiculo = modeloVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.kilometraje = kilometraje;
        this.anioFabricacion = anioFabricacion;
        this.consumoCombustiblePorKm = consumoCombustiblePorKm;
        this.estado = estado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getConsumoCombustiblePorKm() {
        return consumoCombustiblePorKm;
    }

    public void setConsumoCombustiblePorKm(String consumoCombustiblePorKm) {
        this.consumoCombustiblePorKm = consumoCombustiblePorKm;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public void setEstado(boolean estado){
        this.estado = estado;
    }
}
