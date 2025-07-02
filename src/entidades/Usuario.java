package entidades;

import java.sql.Timestamp;

public class Usuario extends Persona{
    
    private int id;
    
    private String rol;
    private String nombreUsuario;
    private String contrasena;
    
    private String estado;           

    public Usuario() {
    }

    public Usuario(int id, String cedula, String nombreCompleto, String correoElectronico,
                   String telefono, String direccion, String rol, String nombreUsuario,
                   String contrasena, String estado, Timestamp fechaRegistro) {
        super(); // Llama al constructor por defecto

        this.setCedula(cedula);
        this.setNombreCompleto(nombreCompleto);
        this.setCorreoElectronico(correoElectronico);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setFechaRegistro(fechaRegistro);

        this.id = id;
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }


    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

