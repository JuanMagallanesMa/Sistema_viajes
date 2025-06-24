package entidades;

/**
 *
 * @author MICHELLE
 */
public class Cliente {

    public int id;
    public String  nombres_apellidos, correoelectronico, direccion, telefono, cedula;
    public java.sql.Timestamp fecha_registro;

    public Cliente() {
    }

    public Cliente(int id, String nombres_apellidos, String correoelectronico, String direccion, String telefono, String cedula, java.sql.Timestamp fecha_registro) {
        this.id = id;
        this.nombres_apellidos = nombres_apellidos;
        this.correoelectronico = correoelectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cedula = cedula;
        this.fecha_registro = fecha_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres_apellidos() {
        return nombres_apellidos;
    }

    public void setNombres_apellidos(String nombres_apellidos) {
        this.nombres_apellidos = nombres_apellidos;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public java.sql.Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(java.sql.Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombres_apellidos=" + nombres_apellidos + ", correoelectronico=" + correoelectronico + ", direccion=" + direccion + ", telefono=" + telefono + ", cedula=" + cedula + ", fecha_registro=" + fecha_registro + '}';
    }

}
