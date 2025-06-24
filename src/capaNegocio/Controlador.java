package capaNegocio;

import static capaDatos.Conexion.getConexion;
import entidades.Cliente;
import entidades.Factura;
import entidades.Reserva;
import entidades.Usuario;
import entidades.Transporte;
import entidades.PaqueteTuristico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Controlador {

    public boolean Login(String usuario, String clave) {
        String query = "SELECT * FROM vendedor WHERE usuario = ? AND contrasenia = ?";

        try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(query)) {

            // Establecer los parámetros de la consulta
            pst.setString(1, usuario);
            pst.setString(2, clave);

            // Ejecutar la consulta
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "BIENVENIDOS A SU SISTEMA", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void InsertarCliente(Cliente cli) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("INSERT INTO cliente (nombres_apellidos, correoelectronico, direccion, telefono, cedula, fecha_registro) VALUES(?,?,?,?,?,?)");
            pst.setString(1, cli.getNombres_apellidos());
            pst.setString(2, cli.getCorreoelectronico());
            pst.setString(3, cli.getDireccion());
            pst.setString(4, cli.getTelefono());
            pst.setString(5, cli.getCedula());
            pst.setTimestamp(6, cli.getFecha_registro());
            pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Guardado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en los datos");
        }
    }

    public void ModificarCliente(Cliente cli) {
        try {
            Connection cone = getConexion();

            String sql = "UPDATE cliente SET nombres_apellidos=?, correoelectronico=?, direccion=?, telefono=?, cedula=? WHERE id=?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setString(1, cli.getNombres_apellidos());
            pst.setString(2, cli.getCorreoelectronico());
            pst.setString(3, cli.getDireccion());
            pst.setString(4, cli.getTelefono());
            pst.setString(5, cli.getCedula());
            pst.setInt(6, cli.getId());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Modificado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);

                System.out.println("cliente modificado correctamente");
            } else {
                System.out.println("No se encontró el registro para modificar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al modificar los datos");
        }
    }

    public void EliminarCliente(int id) {
        try {
            Connection cone = getConexion();
            String sql = "DELETE FROM cliente WHERE id=?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setInt(1, id);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
             JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);

                System.out.println("Registro eliminado correctamente");
            } else {
                System.out.println("No se encontró el registro para eliminar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al eliminar los datos");
        }
    }

    public void InsertarReserva(Reserva re) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("INSERT INTO reserva (cliente_id, destino, origen, fecha_reserva, fecha_viaje, cantidad_pasajeros, clase, precio_total, estado) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, re.getCliente_id());
            pst.setString(2, re.getDestino());
            pst.setString(3, re.getOrigen());
            pst.setTimestamp(4, re.getFecha_reserva());
            pst.setTimestamp(5, re.getFecha_viaje());
            pst.setDouble(6, re.getCantidad_pasajeros());
            pst.setString(7, re.getClase());
            pst.setDouble(8, re.getPrecio_total());
            pst.setString(9, re.getEstado());

            pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Reserva generada correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en los datos");
        }
    }

    public void ModificarReserva(Reserva res) {
        try {
            Connection cone = getConexion();

            String sql = "UPDATE reserva SET cliente_id=?, destino=?, origen=?, fecha_reserva=?, fecha_viaje=?, cantidad_pasajeros=?, clase=?, precio_total=?, estado=? WHERE id=?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setInt(1, res.getCliente_id());
            pst.setString(2, res.getDestino());
            pst.setString(3, res.getOrigen());
            pst.setTimestamp(4, res.getFecha_reserva());
            pst.setTimestamp(5, res.getFecha_viaje());
            pst.setDouble(6, res.getCantidad_pasajeros());
            pst.setString(7, res.getClase());
            pst.setDouble(8, res.getPrecio_total());
            pst.setString(9, res.getEstado());
            pst.setInt(10, res.getId());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("cliente modificado correctamente");
            } else {
                System.out.println("No se encontró el registro para modificar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al modificar los datos");
        }
    }

    public void EliminarReserva(int id) {
        try {
            Connection cone = getConexion();
            String sql = "DELETE FROM reserva WHERE id=?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setInt(1, id);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registro eliminado correctamente");
            } else {
                System.out.println("No se encontró el registro para eliminar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al eliminar los datos");
        }
    }
    
    public ArrayList<Reserva> buscarReservas(
            String origen, 
            String destino, 
            java.sql.Timestamp fechaInicio, 
            java.sql.Timestamp fechaFin, 
            String cedulaCliente) {

        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Connection con = getConexion()) {
            // Base de la consulta con JOIN para obtener la cédula del cliente
            String sql = "SELECT r.id, r.cliente_id, r.destino, r.origen, r.fecha_reserva, r.fecha_viaje, " +
                         "r.cantidad_pasajeros, r.clase, r.precio_total, r.estado, c.cedula, c.nombres_apellidos " +
                         "FROM reserva r " +
                         "JOIN cliente c ON r.cliente_id = c.id " +
                         "WHERE 1=1 "; // para facilitar concatenar condiciones

            // Lista para parámetros
            ArrayList<Object> parametros = new ArrayList<>();

            // Agregar condiciones dinámicas si los parámetros no son nulos o vacíos
            if (origen != null && !origen.isEmpty()) {
                sql += " AND r.origen = ? ";
                parametros.add(origen);
            }

            if (destino != null && !destino.isEmpty()) {
                sql += " AND r.destino = ? ";
                parametros.add(destino);
            }

            if (fechaInicio != null) {
                sql += " AND r.fecha_viaje >= ? ";
                parametros.add(fechaInicio);
            }

            if (fechaFin != null) {
                sql += " AND r.fecha_viaje <= ? ";
                parametros.add(fechaFin);
            }

            if (cedulaCliente != null && !cedulaCliente.isEmpty()) {
                sql += " AND c.cedula = ? ";
                parametros.add(cedulaCliente);
            }

            PreparedStatement pst = con.prepareStatement(sql);

            // Setear parámetros en PreparedStatement
            for (int i = 0; i < parametros.size(); i++) {
                pst.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setCliente_id(rs.getInt("cliente_id"));
                r.setDestino(rs.getString("destino"));
                r.setOrigen(rs.getString("origen"));
                r.setFecha_reserva(rs.getTimestamp("fecha_reserva"));
                r.setFecha_viaje(rs.getTimestamp("fecha_viaje"));
                r.setCantidad_pasajeros(rs.getDouble("cantidad_pasajeros"));
                r.setClase(rs.getString("clase"));
                r.setPrecio_total(rs.getDouble("precio_total"));
                r.setEstado(rs.getString("estado"));
                r.setNombres_apellidos(rs.getString("nombres_apellidos")); // Nombre cliente
                // Puedes añadir también la cédula si la clase Reserva tiene ese atributo

                reservas.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("Error en búsqueda de reservas: " + ex.getMessage());
        }

        return reservas;
    }
 
    public void EmitirFactura(Factura fa) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("INSERT INTO factura (reserva_id, fecha_emision, metodo_pago, subtotal, iva, total, estado) VALUES(?,?,?,?,?,?,?)");
            pst.setInt(1, fa.getReserva_id()); // reserva_id
            pst.setTimestamp(2, fa.getFecha_emision()); // fecha_emision
            pst.setString(3, fa.getMetodo()); // metodo_pago
            pst.setDouble(4, fa.getSubTotal()); // subtotal
            pst.setDouble(5, fa.getIva()); // iva
            pst.setDouble(6, fa.getTotal()); // total
            pst.setString(7, fa.getEstado()); // estado

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Factura emitida correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en los datos");
        }
    }

    public void ModificarFactura(Factura fa) {
        try {
            Connection cone = getConexion();

            String sql = "UPDATE factura SET reserva_id=?, fecha_emision=?, metodo_pago=?, subtotal=?, iva=?, total=?, estado=? WHERE id=?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setInt(1, fa.getReserva_id()); // reserva_id
            pst.setTimestamp(2, fa.getFecha_emision()); // fecha_emision
            pst.setString(3, fa.getMetodo()); // metodo_pago
            pst.setDouble(4, fa.getSubTotal()); // subtotal
            pst.setDouble(5, fa.getIva()); // iva
            pst.setDouble(6, fa.getTotal()); // total
            pst.setString(7, fa.getEstado()); // estado
            pst.setInt(8, fa.getId());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                
                System.out.println("factura modificada correctamente");
            } else {
                System.out.println("No se encontró el registro para modificar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al modificar los datos");
        }
    }

    public void AnularFactura(int facturaId) {
    try {
        // Obtener la conexión a la base de datos
        Connection cone = getConexion();

        // Preparar la consulta SQL para actualizar el estado de la factura
        PreparedStatement pst = cone.prepareStatement("UPDATE factura SET estado = ? WHERE id = ?");
        
        // Establecer los valores en los parámetros
        pst.setString(1, "ANULADO"); // Cambiar el estado a "anulado"
        pst.setInt(2, facturaId);    // ID de la factura a anular

        // Ejecutar la consulta
        int rowsAffected = pst.executeUpdate();

        // Verificar si la actualización fue exitosa
        if (rowsAffected > 0) {
            System.out.println("Factura anulada correctamente.");
        } else {
            System.out.println("No se encontró una factura con el ID proporcionado.");
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        System.out.println("Error al intentar anular la factura.");
    }
}

    public Cliente obtenerClientePorCedula(String cedula) {
        Cliente cliente = null;

        try (Connection conn = getConexion()) {
            String query = "SELECT * FROM cliente WHERE cedula = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, cedula);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombres_apellidos"),
                        rs.getString("correoelectronico"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("cedula"),
                        rs.getTimestamp("fecha_registro")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e.getMessage());
        }

        return cliente;
    }

    public Cliente obtenerClientePorNombre(String nombre) {
        Cliente cliente = null;
        try {
            Connection cone = getConexion();
            String query = "SELECT * FROM cliente WHERE nombres_apellidos = ?";
            PreparedStatement pst = cone.prepareStatement(query);
            pst.setString(1, nombre);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id")); // ID del cliente
                cliente.setNombres_apellidos(rs.getString("nombres_apellidos"));
                cliente.setCedula(rs.getString("cedula"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cliente: " + ex.getMessage());
        }
        return cliente;
    }

    public Reserva obtenerReservaPorCedula(String cedula) {
        Reserva reserva = null;
        String sql = "SELECT r.id, r.cliente_id, r.destino, r.origen, r.fecha_reserva, r.fecha_viaje, "
                + "r.cantidad_pasajeros, r.clase, r.precio_total, r.estado, c.nombres_apellidos "
                + "FROM Reserva r "
                + "JOIN Cliente c ON r.cliente_id = c.id "
                + "WHERE c.cedula = ?";

        try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setCliente_id(rs.getInt("cliente_id"));
                reserva.setDestino(rs.getString("destino"));
                reserva.setOrigen(rs.getString("origen"));
                reserva.setFecha_reserva(rs.getTimestamp("fecha_reserva"));
                reserva.setFecha_viaje(rs.getTimestamp("fecha_viaje"));
                reserva.setCantidad_pasajeros(rs.getDouble("cantidad_pasajeros"));
                reserva.setClase(rs.getString("clase"));
                reserva.setPrecio_total(rs.getDouble("precio_total"));
                reserva.setEstado(rs.getString("estado"));
                reserva.setNombres_apellidos(rs.getString("nombres_apellidos")); // Agregar nombre cliente
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar reserva: " + ex.getMessage());
        }
        return reserva;
    }

    public Factura obtenerFacturaPorReserva(int reservaId) {
        Factura factura = null;
        try (Connection cone = getConexion()) {
            String sql = "SELECT * FROM factura WHERE reserva_id = ?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setInt(1, reservaId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                factura = new Factura();
                factura.setId(rs.getInt("id"));
                factura.setReserva_id(rs.getInt("reserva_id"));
                factura.setFecha_emision(rs.getTimestamp("fecha_emision"));
                factura.setMetodo(rs.getString("metodo_pago"));
                factura.setSubTotal(rs.getDouble("subtotal"));
                factura.setIva(rs.getDouble("iva"));
                factura.setTotal(rs.getDouble("total"));
                factura.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la factura: " + ex.getMessage());
        }
        return factura;
    }

    public ArrayList<Cliente> ListaCliente() {
        ArrayList<Cliente> client = new ArrayList();
        try {
            //conectamos
            Connection cone = getConexion();
            Statement st = cone.createStatement();
            //se indica los campos y la tabla para mostrar
            ResultSet resul = st.executeQuery("  SELECT id,nombres_apellidos,correoelectronico,direccion,telefono,cedula,fecha_registro"
                    + "    FROM cliente ORDER BY 1  ");
            while (resul.next()) {
                Cliente c = new Cliente();
                c.setId(resul.getInt("id"));
                c.setNombres_apellidos(resul.getString("nombres_apellidos"));
                c.setCorreoelectronico(resul.getString("correoelectronico"));
                c.setDireccion(resul.getString("direccion"));
                c.setTelefono(resul.getString("telefono"));
                c.setCedula(resul.getString("cedula"));
                c.setFecha_registro(resul.getTimestamp("fecha_registro"));

                client.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("HA OCURRIDO UN ERROR");
        }
        return client;
    }

    public ArrayList<Reserva> ListaReserva() {
        ArrayList<Reserva> client = new ArrayList();
        try {
            //conectamos
            Connection cone = getConexion();
            Statement st = cone.createStatement();
            //se indica los campos y la tabla para mostrar
            ResultSet resul = st.executeQuery("  SELECT id,cliente_id, destino, origen, fecha_reserva, fecha_viaje, cantidad_pasajeros, clase, precio_total, estado"
                    + "    FROM reserva ORDER BY 1  ");
            while (resul.next()) {
                Reserva c = new Reserva();
                c.setId(resul.getInt("id"));
                //c.setCliente_id(resul.getInt("cliente_id"));
                c.setNombres_apellidos(resul.getString("cliente_id"));
                c.setDestino(resul.getString("destino"));
                c.setOrigen(resul.getString("origen"));
                c.setFecha_reserva(resul.getTimestamp("fecha_reserva"));
                c.setFecha_viaje(resul.getTimestamp("fecha_viaje"));
                c.setCantidad_pasajeros(resul.getDouble("cantidad_pasajeros"));
                c.setClase(resul.getString("clase"));
                c.setPrecio_total(resul.getDouble("precio_total"));
                c.setEstado(resul.getString("estado"));

                client.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("HA OCURRIDO UN ERROR");
        }
        return client;
    }

    public ArrayList<Factura> ListaFactura() {
        ArrayList<Factura> client = new ArrayList();
        try {
            //conectamos
            Connection cone = getConexion();
            Statement st = cone.createStatement();
            //se indica los campos y la tabla para mostrar
            ResultSet resul = st.executeQuery("  SELECT id,reserva_id, fecha_emision, metodo_pago, subtotal, iva, total, estado"
                    + "    FROM factura ORDER BY 1  ");
            while (resul.next()) {
                Factura c = new Factura();
                c.setId(resul.getInt("id"));
                c.setReserva_id(resul.getInt("reserva_id"));
                c.setFecha_emision(resul.getTimestamp("fecha_emision"));
                c.setMetodo(resul.getString("metodo_pago"));
                c.setSubTotal(resul.getDouble("subtotal"));
                c.setIva(resul.getDouble("iva"));
                c.setTotal(resul.getDouble("total"));
                c.setEstado(resul.getString("estado"));

                client.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("HA OCURRIDO UN ERROR");
        }
        return client;
    }
    
    /**
 * Modulo Usuario
     * @param user
 */
    public void insertarUsuario(Usuario user) {
    try {
        Connection cone = getConexion();
        PreparedStatement pst = cone.prepareStatement(
            "INSERT INTO usuario (cedula, nombre_completo, correo_electronico, telefono, direccion, rol, nombre_usuario, contrasena, fecha_registro, estado) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        pst.setString(1, user.getCedula());
        pst.setString(2, user.getNombreCompleto());
        pst.setString(3, user.getCorreoElectronico());
        pst.setString(4, user.getTelefono());
        pst.setString(5, user.getDireccion());
        pst.setString(6, user.getRol());
        pst.setString(7, user.getNombreUsuario());
        pst.setString(8, user.getContrasena());
        pst.setTimestamp(9, user.getFechaRegistro());
        pst.setString(10, user.getEstado());

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error al registrar usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

    public void modificarUsuario(Usuario user) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement(
                "UPDATE usuario SET cedula=?, nombre_completo=?, correo_electronico=?, telefono=?, direccion=?, rol=?, nombre_usuario=?, contrasena=?, fecha_registro=?, estado=? WHERE id=?");

            pst.setString(1, user.getCedula());
            pst.setString(2, user.getNombreCompleto());
            pst.setString(3, user.getCorreoElectronico());
            pst.setString(4, user.getTelefono());
            pst.setString(5, user.getDireccion());
            pst.setString(6, user.getRol());
            pst.setString(7, user.getNombreUsuario());
            pst.setString(8, user.getContrasena());
            pst.setTimestamp(9, user.getFechaRegistro());
            pst.setString(10, user.getEstado());
            pst.setInt(11, user.getId());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Usuario modificado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el usuario a modificar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Usuario> listaUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection cone = getConexion();
            Statement st = cone.createStatement();
            ResultSet resul = st.executeQuery(
                "SELECT * FROM usuario ORDER BY id");

            while (resul.next()) {
                Usuario u = new Usuario();
                u.setId(resul.getInt("id"));
                u.setCedula(resul.getString("cedula"));
                u.setNombreCompleto(resul.getString("nombre_completo"));
                u.setCorreoElectronico(resul.getString("correo_electronico"));
                u.setTelefono(resul.getString("telefono"));
                u.setDireccion(resul.getString("direccion"));
                u.setRol(resul.getString("rol"));
                u.setNombreUsuario(resul.getString("nombre_usuario"));
                u.setContrasena(resul.getString("contrasena"));
                u.setFechaRegistro(resul.getTimestamp("fecha_registro"));
                u.setEstado(resul.getString("estado"));

                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar lista de usuarios", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return usuarios;
    }
    
    public void eliminarUsuario(int id) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("UPDATE usuario SET estado = ? WHERE id = ?");
            pst.setString(1, "Inactivo");
            pst.setInt(2, id);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Usuario marcado como inactivo correctamente",
                        "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el usuario a eliminar",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar (inhabilitar) usuario",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Usuario obtenerUsuarioPorCedula(String cedula) {
        Usuario usuario = null;
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement(
                "SELECT * FROM usuario WHERE cedula = ?");
            pst.setString(1, cedula);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setRol(rs.getString("rol"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                usuario.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar usuario por cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return usuario;
    }
    
    // Modulo de Transporte
    
    public void insertarTransporte(Transporte transporte) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement(
                "INSERT INTO transporte (placa, modelo_vehiculo, tipo_vehiculo, kilometraje, anio_fabricacion, consumo_combustible_por_km, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            pst.setString(1, transporte.getPlaca());
            pst.setString(2, transporte.getModeloVehiculo());
            pst.setString(3, transporte.getTipoVehiculo());
            pst.setInt(4, transporte.getKilometraje());
            pst.setInt(5, transporte.getAnioFabricacion());
            pst.setString(6, transporte.getConsumoCombustiblePorKm());
            pst.setBoolean(7, transporte.getEstado());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transporte registrado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al registrar transporte", "ERROR", JOptionPane.ERROR_MESSAGE);
        }  
    }  
    
    public void modificarTransporte(Transporte transporte) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement(
                "UPDATE transporte SET modelo_vehiculo=?, tipo_vehiculo=?, kilometraje=?, anio_fabricacion=?, consumo_combustible_por_km=?, estado=? WHERE placa=?"
            );

            pst.setString(1, transporte.getModeloVehiculo());
            pst.setString(2, transporte.getTipoVehiculo());
            pst.setInt(3, transporte.getKilometraje());
            pst.setInt(4, transporte.getAnioFabricacion());
            pst.setString(5, transporte.getConsumoCombustiblePorKm());
            pst.setBoolean(6, transporte.getEstado());
            pst.setString(7, transporte.getPlaca());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Transporte modificado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el transporte a modificar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar transporte", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarTransporte(String placa) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("UPDATE transporte SET estado = ? WHERE placa = ?");
            pst.setBoolean(1, false); // Estado inactivo
            pst.setString(2, placa);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Transporte marcado como inactivo correctamente",
                        "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el transporte a eliminar",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar (inhabilitar) transporte",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Este metodo servira para traer de la tabla de Transporte las placas
    // y ubicarlas en el combobox de paquete turistico
    public ArrayList<String> obtenerPlacasDisponibles() {
        ArrayList<String> placas = new ArrayList<>();
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("SELECT placa FROM transporte WHERE estado = true");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                placas.add(rs.getString("placa"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar placas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return placas;
    }
    
    public ArrayList<Transporte> listarTransporte() {
        ArrayList<Transporte> lista = new ArrayList<>();
        try {
            Connection cone = getConexion();
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM transporte ORDER BY placa");

            while (rs.next()) {
                Transporte t = new Transporte();
                t.setPlaca(rs.getString("placa"));
                t.setModeloVehiculo(rs.getString("modelo_vehiculo"));
                t.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                t.setKilometraje(rs.getInt("kilometraje"));
                t.setAnioFabricacion(rs.getInt("anio_fabricacion"));
                t.setConsumoCombustiblePorKm(rs.getString("consumo_combustible_por_km"));
                t.setEstado(rs.getBoolean("estado"));

                lista.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar lista de transporte", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
    public Transporte obtenerTransportePorPlaca(String placa) {
        Transporte transporte = null;
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("SELECT * FROM transporte WHERE placa = ?");
            pst.setString(1, placa);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                transporte = new Transporte();
                transporte.setPlaca(rs.getString("placa"));
                transporte.setModeloVehiculo(rs.getString("modelo_vehiculo"));
                transporte.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                transporte.setKilometraje(rs.getInt("kilometraje"));
                transporte.setAnioFabricacion(rs.getInt("anio_fabricacion"));
                transporte.setConsumoCombustiblePorKm(rs.getString("consumo_combustible_por_km"));
                transporte.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar transporte por placa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return transporte;
    }
    
    public ArrayList<Transporte> obtenerTransportePorEstado(boolean estado) {
        ArrayList<Transporte> lista = new ArrayList<>();
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("SELECT * FROM transporte WHERE estado = ?");
            pst.setBoolean(1, estado);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transporte t = new Transporte();
                t.setPlaca(rs.getString("placa"));
                t.setModeloVehiculo(rs.getString("modelo_vehiculo"));
                t.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                t.setKilometraje(rs.getInt("kilometraje"));
                t.setAnioFabricacion(rs.getInt("anio_fabricacion"));
                t.setConsumoCombustiblePorKm(rs.getString("consumo_combustible_por_km"));
                t.setEstado(rs.getBoolean("estado"));

                lista.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar transporte por estado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
    // Este metodo es una combinacion, como no veo logico que la consulta sea por estado
    // se unio en una sola linea SQL para que haga la consulta y por ende para la parte
    // visual el campo de placa y estado serian obligatorios
    public Transporte obtenerTransportePorPlacaYEstado(String placa, boolean estado) {
        Transporte transporte = null;
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("SELECT * FROM transporte WHERE placa = ? AND estado = ?");
            pst.setString(1, placa);
            pst.setBoolean(2, estado);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                transporte = new Transporte();
                transporte.setPlaca(rs.getString("placa"));
                transporte.setModeloVehiculo(rs.getString("modelo_vehiculo"));
                transporte.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                transporte.setKilometraje(rs.getInt("kilometraje"));
                transporte.setAnioFabricacion(rs.getInt("anio_fabricacion"));
                transporte.setConsumoCombustiblePorKm(rs.getString("consumo_combustible_por_km"));
                transporte.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar transporte por placa y estado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return transporte;
    }
    
    // Modulo de Paquete turistico
    
    public void insertarPaqueteTuristico(PaqueteTuristico paquete) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement(
                "INSERT INTO paquete_turistico (nombre_paquete, destino, precio_destino, hospedaje, transporte_placa, actividades, duracion_dias, fecha_inicio, fecha_fin, precio_total) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pst.setString(1, paquete.getNombrePaquete());
            pst.setString(2, paquete.getDestino());
            pst.setDouble(3, paquete.getPrecioDestino());
            pst.setString(4, paquete.getHospedaje());
            pst.setString(5, paquete.getTransportePlaca());
            pst.setString(6, paquete.getActividades());
            pst.setInt(7, paquete.getDuracionDias());
            pst.setString(8, paquete.getFechaInicio());
            pst.setString(9, paquete.getFechaFin());
            pst.setDouble(10, paquete.getPrecioTotal());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paquete turístico registrado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al registrar paquete turístico", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void modificarPaqueteTuristico(PaqueteTuristico paquete) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement(
                "UPDATE paquete_turistico SET destino=?, precio_destino=?, hospedaje=?, transporte_placa=?, actividades=?, duracion_dias=?, fecha_inicio=?, fecha_fin=?, precio_total=? WHERE nombre_paquete=?");

            pst.setString(1, paquete.getDestino());
            pst.setDouble(2, paquete.getPrecioDestino());
            pst.setString(3, paquete.getHospedaje());
            pst.setString(4, paquete.getTransportePlaca());
            pst.setString(5, paquete.getActividades());
            pst.setInt(6, paquete.getDuracionDias());
            pst.setString(7, paquete.getFechaInicio());
            pst.setString(8, paquete.getFechaFin());
            pst.setDouble(9, paquete.getPrecioTotal());
            pst.setString(10, paquete.getNombrePaquete());

            int updated = pst.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Paquete turístico modificado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el paquete turístico a modificar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar paquete turístico", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarPaqueteTuristico(String nombrePaquete) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("DELETE FROM paquete_turistico WHERE nombre_paquete = ?");
            pst.setString(1, nombrePaquete);

            int deleted = pst.executeUpdate();
            if (deleted > 0) {
                JOptionPane.showMessageDialog(null, "Paquete turístico eliminado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el paquete a eliminar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar paquete turístico", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<PaqueteTuristico> listarPaqueteTuristico() {
        ArrayList<PaqueteTuristico> lista = new ArrayList<>();
        try {
            Connection cone = getConexion();
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM paquete_turistico");

            while (rs.next()) {
                PaqueteTuristico p = new PaqueteTuristico();
                p.setNombrePaquete(rs.getString("nombre_paquete"));
                p.setDestino(rs.getString("destino"));
                p.setPrecioDestino(rs.getDouble("precio_destino"));
                p.setHospedaje(rs.getString("hospedaje"));
                p.setTransportePlaca(rs.getString("transporte_placa"));
                p.setActividades(rs.getString("actividades"));
                p.setDuracionDias(rs.getInt("duracion_dias"));
                p.setFechaInicio(rs.getString("fecha_inicio"));
                p.setFechaFin(rs.getString("fecha_fin"));
                p.setPrecioTotal(rs.getDouble("precio_total"));

                lista.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al listar paquetes turísticos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
    public PaqueteTuristico obtenerPaquetePorNombre(String nombrePaquete) {
        PaqueteTuristico paquete = null;
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("SELECT * FROM paquete_turistico WHERE nombre_paquete = ?");
            pst.setString(1, nombrePaquete);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                paquete = new PaqueteTuristico();
                paquete.setNombrePaquete(rs.getString("nombre_paquete"));
                paquete.setDestino(rs.getString("destino"));
                paquete.setPrecioDestino(rs.getDouble("precio_destino"));
                paquete.setHospedaje(rs.getString("hospedaje"));
                paquete.setTransportePlaca(rs.getString("transporte_placa"));
                paquete.setActividades(rs.getString("actividades"));
                paquete.setDuracionDias(rs.getInt("duracion_dias"));
                paquete.setFechaInicio(rs.getString("fecha_inicio"));
                paquete.setFechaFin(rs.getString("fecha_fin"));
                paquete.setPrecioTotal(rs.getDouble("precio_total"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar paquete turístico por nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return paquete;
    }
}
