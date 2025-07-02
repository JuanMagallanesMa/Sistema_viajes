package entidades;
import java.sql.Timestamp;
/**
 *
 * @author MICHELLE
 */
public class Cliente extends Persona {

    public int id;
    

    public Cliente() {
    }

   
    public Cliente(int id, String cedula, String nombreCompleto, String correoElectronico,
                   String telefono, String direccion,  Timestamp fechaRegistro) {
        super(); // Constructor de Persona

        this.setCedula(cedula);
        this.setNombreCompleto(nombreCompleto);
        this.setCorreoElectronico(correoElectronico);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setFechaRegistro(fechaRegistro);

        this.id = id;
        
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    

}
