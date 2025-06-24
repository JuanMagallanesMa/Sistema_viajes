
package capaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author MICHELLE
 */
public class Conexion {
      public static Connection getConexion() {
        String conUrl = "jdbc:postgresql://localhost/sistema_de_viajes";
        String usuario = "postgres";
        String contra = "12345";

        try {
            Connection con = DriverManager.getConnection(conUrl, usuario, contra);
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }  
}

    

