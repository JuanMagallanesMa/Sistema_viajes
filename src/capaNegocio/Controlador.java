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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;


public class Controlador {

    public boolean Login(String usuarioIngresado, String claveIngresada) {
        String queryLogin = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasena = ?";

        try (Connection con = getConexion()) {

            // Validar usuario por defecto en cualquier caso
            if (usuarioIngresado.equals("admin") && claveIngresada.equals("12345")) {
                JOptionPane.showMessageDialog(null, "BIENVENIDO USUARIO POR DEFECTO (admin)", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

            // Validar contra la base de datos
            try (PreparedStatement pst = con.prepareStatement(queryLogin)) {
                pst.setString(1, usuarioIngresado);
                pst.setString(2, claveIngresada);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "BIENVENIDO " + rs.getString("nombre_completo"), "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

 public static ArrayList<Cliente> buscarClientes(String nombre, String cedula, Timestamp desde, Timestamp hasta) {
        ArrayList<Cliente> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM cliente WHERE 1=1");

        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" AND LOWER(nombres_apellidos) LIKE ?");
        }
        if (cedula != null && !cedula.trim().isEmpty()) {
            sql.append(" AND cedula LIKE ?");
        }
        if (desde != null) {
            sql.append(" AND fecha_registro >= ?");
        }
        if (hasta != null) {
            sql.append(" AND fecha_registro <= ?");
        }

        try (Connection con = getConexion();
             PreparedStatement pst = con.prepareStatement(sql.toString())) {

            int index = 1;

            if (nombre != null && !nombre.trim().isEmpty()) {
                pst.setString(index++, "%" + nombre.toLowerCase() + "%");
            }
            if (cedula != null && !cedula.trim().isEmpty()) {
                pst.setString(index++, "%" + cedula + "%");
            }
            if (desde != null) {
                pst.setTimestamp(index++, desde);
            }
            if (hasta != null) {
                pst.setTimestamp(index++, hasta);
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombres_apellidos(rs.getString("nombres_apellidos"));
                c.setCorreoelectronico(rs.getString("correoelectronico"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                c.setCedula(rs.getString("cedula"));
                c.setFecha_registro(rs.getTimestamp("fecha_registro"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
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
    
    public Map<String, Integer> obtenerClientesConId() {
        Map<String, Integer> clientes = new HashMap<>();
        String sql = "SELECT id, nombres_apellidos FROM cliente";

        try (Connection con = getConexion();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                clientes.put(rs.getString("nombres_apellidos"), rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes con ID: " + e.getMessage());
        }

        return clientes;
    }
    public static ArrayList<Reserva> buscarReservas(String origen, String destino, String clase,
                                                    String estado, Timestamp desde, Timestamp hasta) {
        ArrayList<Reserva> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT r.id, r.cliente_id, c.nombres_apellidos, r.origen, r.destino, r.clase, r.estado, " +
            "r.cantidad_pasajeros, r.precio_total, r.fecha_reserva, r.fecha_viaje " +
            "FROM reserva r JOIN cliente c ON r.cliente_id = c.id WHERE 1=1"
        );

        if (origen != null && !origen.isEmpty()) sql.append(" AND LOWER(r.origen) = LOWER(?)");
        if (destino != null && !destino.isEmpty()) sql.append(" AND LOWER(r.destino) = LOWER(?)");
        if (clase != null && !clase.isEmpty()) sql.append(" AND r.clase = ?");
        if (estado != null && !estado.isEmpty()) sql.append(" AND r.estado = ?");
        if (desde != null) sql.append(" AND r.fecha_viaje >= ?");
        if (hasta != null) sql.append(" AND r.fecha_viaje <= ?");

        try (Connection con = getConexion();
             PreparedStatement pst = con.prepareStatement(sql.toString())) {

            int idx = 1;

            if (origen != null && !origen.isEmpty()) pst.setString(idx++, origen);
            if (destino != null && !destino.isEmpty()) pst.setString(idx++, destino);
            if (clase != null && !clase.isEmpty()) pst.setString(idx++, clase);
            if (estado != null && !estado.isEmpty()) pst.setString(idx++, estado);
            if (desde != null) pst.setTimestamp(idx++, desde);
            if (hasta != null) pst.setTimestamp(idx++, hasta);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reserva r = new Reserva();
                r.id = rs.getInt("id");
                r.cliente_id = rs.getInt("cliente_id");
                r.nombres_apellidos = rs.getString("nombres_apellidos");
                r.origen = rs.getString("origen");
                r.destino = rs.getString("destino");
                r.clase = rs.getString("clase");
                r.estado = rs.getString("estado");
                r.cantidad_pasajeros = rs.getInt("cantidad_pasajeros");
                r.precio_total = rs.getDouble("precio_total");
                r.fecha_reserva = rs.getTimestamp("fecha_reserva");
                r.fecha_viaje = rs.getTimestamp("fecha_viaje");
                lista.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
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
            pst.setInt(6, re.getCantidad_pasajeros());
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

            String sql = "UPDATE reserva SET cliente_id=?, destino=?, origen=?, fecha_reserva=?, "
                    + "fecha_viaje=?, cantidad_pasajeros=?, clase=?, precio_total=?, estado=? WHERE id=?";
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
    public void eliminarReservaLogica(int id) {
        try {
            Connection cone = getConexion();
            String sql = "UPDATE reserva SET estado = ? WHERE id = ?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setString(1, "Cancelada");  // O el estado que uses para marcar eliminado
            pst.setInt(2, id);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registro marcado como eliminado correctamente");
            } else {
                System.out.println("No se encontró el registro para marcar como eliminado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al actualizar el estado de la reserva");
        }
    }
    public List<Reserva> obtenerReservas() {
    List<Reserva> lista = new ArrayList<>();
    String sql = "SELECT r.id, c.nombres_apellidos, r.precio_total " +
                 "FROM reserva r " +
                 "JOIN cliente c ON r.cliente_id = c.id " +
                 "WHERE r.estado = 'Confirmada'"; // si quieres filtrar solo confirmadas, opcional

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            Reserva r = new Reserva();
            r.setId(rs.getInt("id"));
            r.setNombres_apellidos(rs.getString("nombres_apellidos"));
            r.setPrecio_total(rs.getDouble("precio_total"));
            lista.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error al obtener reservas", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    return lista;
}
    
    public List<Reserva> obtenerReservasParaFactura() {
    List<Reserva> lista = new ArrayList<>();
    String sql = "SELECT r.id, r.precio_total, c.nombres_apellidos " +
                 "FROM reserva r " +
                 "JOIN cliente c ON r.cliente_id = c.id " +
                 "WHERE r.estado = 'Confirmado'";  // puedes ajustar esta condición según lo que necesites

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Reserva r = new Reserva();
            r.id = rs.getInt("id");
            r.precio_total = rs.getDouble("precio_total");
            r.nombres_apellidos = rs.getString("nombres_apellidos");

            lista.add(r);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al obtener reservas", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    return lista;
}
    
    
    
    
    
    
    public int buscarClienteIdPorNombre(String nombreCliente) {
    int id = -1;
    String sql = "SELECT id FROM cliente WHERE nombres_apellidos = ?";
    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, nombreCliente);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException ex) {
        System.out.println("Error al obtener cliente_id: " + ex.getMessage());
    }
    return id;
}

    private final Map<String, Double> tablaPrecios = new HashMap<>();
    
    private void cargarPrecios() {
        tablaPrecios.put("Ecuador-Perú", 200.0);
        tablaPrecios.put("Ecuador-México", 400.0);
        tablaPrecios.put("Perú-Ecuador", 200.0);
        tablaPrecios.put("Perú-México", 350.0);
        tablaPrecios.put("México-Ecuador", 400.0);
        tablaPrecios.put("México-Perú", 350.0);
    }
    public double obtenerPrecio(String origen, String destino) {
    if (tablaPrecios.isEmpty()) {
        cargarPrecios(); // Se asegura de que esté cargado
    }

    String clave = origen + "-" + destino;
    return tablaPrecios.getOrDefault(clave, 0.0);
}
   public ArrayList<Reserva> obtenerReservasCmb() {
    ArrayList<Reserva> lista = new ArrayList<>();
    String sql = "SELECT r.id, r.precio_total, c.nombres_apellidos FROM reserva r JOIN cliente c ON r.cliente_id = c.id WHERE r.estado = 'Pendiente'";
    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            Reserva r = new Reserva();
            r.setId(rs.getInt("id"));
            r.setPrecio_total(rs.getDouble("precio_total"));  // ✅ aquí se carga correctamente
            r.setNombres_apellidos(rs.getString("nombres_apellidos"));
            lista.add(r);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener reservas: " + e.getMessage());
    }
    return lista;
}
    
    
    public Reserva obtenerReservaPorId(int id) {
    Reserva r = null;

    String sql = "SELECT r.*, c.nombres_apellidos FROM reserva r JOIN cliente c ON r.cliente_id = c.id WHERE r.id = ?";

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            r = new Reserva();
            r.setId(rs.getInt("id"));
            r.setCliente_id(rs.getInt("cliente_id"));
            r.setOrigen(rs.getString("origen"));
            r.setDestino(rs.getString("destino"));
            r.setFecha_reserva(rs.getTimestamp("fecha_reserva"));
            r.setFecha_viaje(rs.getTimestamp("fecha_viaje"));
            r.setCantidad_pasajeros(rs.getInt("cantidad_pasajeros"));
            r.setClase(rs.getString("clase"));
            r.setPrecio_total(rs.getDouble("precio_total"));
            r.setEstado(rs.getString("estado"));
            r.setNombres_apellidos(rs.getString("nombres_apellidos"));
        }

    } catch (SQLException ex) {
        System.out.println("Error al obtener reserva por ID: " + ex.getMessage());
    }

    return r;
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
                r.setCantidad_pasajeros(rs.getInt("cantidad_pasajeros"));
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
    
    public List<Reserva> obtenerReservasDisponibles() {
    List<Reserva> lista = new ArrayList<>();
    try (Connection cone = getConexion()) {
        String sql = "SELECT r.*, c.nombres_apellidos FROM reserva r JOIN cliente c ON r.cliente_id = c.id";
        PreparedStatement pst = cone.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Reserva r = new Reserva();
            r.setId(rs.getInt("id"));
            r.setCliente_id(rs.getInt("cliente_id"));
            r.setDestino(rs.getString("destino"));
            r.setOrigen(rs.getString("origen"));
            r.setClase(rs.getString("clase"));
            r.setEstado(rs.getString("estado"));
            r.setNombres_apellidos(rs.getString("nombres_apellidos"));
            r.setCantidad_pasajeros(rs.getInt("cantidad_pasajeros"));
            r.setPrecio_total(rs.getDouble("precio_total"));
            r.setFecha_reserva(rs.getTimestamp("fecha_reserva"));
            r.setFecha_viaje(rs.getTimestamp("fecha_viaje"));
            lista.add(r);
        }
    } catch (SQLException ex) {
        System.out.println("Error al cargar reservas: " + ex.getMessage());
    }
    return lista;
}
    
    public Factura obtenerFacturaPorId(int id) {
    Factura factura = null;
    String sql = "SELECT * FROM factura WHERE id = ?";
    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, id);
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
    } catch (SQLException e) {
        System.out.println("Error al buscar factura: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error al buscar factura", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    return factura;
}
public ArrayList<Factura> obtenerFacturasReporte(Date fechaInicio, Date fechaFin, String estado, String metodoPago) {
    ArrayList<Factura> lista = new ArrayList<>();
    String sql = "SELECT * FROM factura WHERE fecha_emision BETWEEN ? AND ?";

    if (!estado.equals("Todos")) {
        sql += " AND estado = ?";
    }
    if (!metodoPago.equals("Todos")) {
        sql += " AND metodo_pago = ?";
    }

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setTimestamp(1, new Timestamp(fechaInicio.getTime()));
        pst.setTimestamp(2, new Timestamp(fechaFin.getTime()));

        int paramIndex = 3;

        if (!estado.equals("Todos")) {
            pst.setString(paramIndex++, estado);
        }

        if (!metodoPago.equals("Todos")) {
            pst.setString(paramIndex, metodoPago);
        }

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Factura f = new Factura();
            f.setId(rs.getInt("id"));
            f.setReserva_id(rs.getInt("reserva_id"));
            f.setFecha_emision(rs.getTimestamp("fecha_emision"));
            f.setMetodo(rs.getString("metodo_pago"));
            f.setSubTotal(rs.getDouble("subtotal"));
            f.setIva(rs.getDouble("iva"));
            f.setTotal(rs.getDouble("total"));
            f.setEstado(rs.getString("estado"));
            lista.add(f);
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener reporte de facturas: " + e.getMessage());
    }

    return lista;
}
    
 
    public void EmitirFactura(Factura fa) {
        try {
            Connection cone = getConexion();
            PreparedStatement pst = cone.prepareStatement("INSERT INTO factura () VALUES(?,?,?,?,?,?,?)");
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
                reserva.setCantidad_pasajeros(rs.getInt("cantidad_pasajeros"));
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
                c.setCantidad_pasajeros(resul.getInt("cantidad_pasajeros"));
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

  public List<Usuario> buscarUsuariosReporte(String cedula, String nombre, String rol, String estado, Timestamp desde, Timestamp hasta) {
    List<Usuario> lista = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM usuario WHERE 1=1");
    
    List<Object> parametros = new ArrayList<>();

    if (cedula != null && !cedula.isEmpty()) {
        sql.append(" AND cedula ILIKE ?");
        parametros.add("%" + cedula + "%");
    }
    if (nombre != null && !nombre.isEmpty()) {
        sql.append(" AND nombre_completo ILIKE ?");
        parametros.add("%" + nombre + "%");
    }
    if (rol != null && !rol.equals("Todos")) {
        sql.append(" AND rol = ?");
        parametros.add(rol);
    }
    if (estado != null && !estado.equals("Todos")) {
        sql.append(" AND estado = ?");
        parametros.add(estado);
    }
    if (desde != null) {
        sql.append(" AND fecha_registro >= ?");
        parametros.add(desde);
    }
    if (hasta != null) {
        sql.append(" AND fecha_registro <= ?");
        parametros.add(hasta);
    }

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql.toString())) {

        for (int i = 0; i < parametros.size(); i++) {
            Object param = parametros.get(i);
            if (param instanceof Timestamp) {
                pst.setTimestamp(i + 1, (Timestamp) param);
            } else {
                pst.setObject(i + 1, param);
            }
        }

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setCedula(rs.getString("cedula"));
            u.setNombreCompleto(rs.getString("nombre_completo"));
            u.setCorreoElectronico(rs.getString("correo_electronico"));
            u.setTelefono(rs.getString("telefono"));
            u.setDireccion(rs.getString("direccion"));
            u.setRol(rs.getString("rol"));
            u.setNombreUsuario(rs.getString("nombre_usuario"));
            u.setContrasena(rs.getString("contrasena"));
            u.setFechaRegistro(rs.getTimestamp("fecha_registro"));
            u.setEstado(rs.getString("estado"));

            lista.add(u);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al consultar usuarios", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    return lista;
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
                "VALUES (?, ?, ?, ?, ?, ?, true)"
            );

            pst.setString(1, transporte.getPlaca());
            pst.setString(2, transporte.getModeloVehiculo());
            pst.setString(3, transporte.getTipoVehiculo());
            pst.setInt(4, transporte.getKilometraje());
            pst.setInt(5, transporte.getAnioFabricacion());
            pst.setString(6, transporte.getConsumoCombustiblePorKm());
            

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
    
    public static ArrayList<Transporte> buscarTransporte(String placa, String tipo, int anio, Boolean estado) {
        ArrayList<Transporte> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM transporte WHERE 1=1");

        if (!placa.isEmpty()) sql.append(" AND LOWER(placa) LIKE ?");
        if (!tipo.isEmpty()) sql.append(" AND tipo_vehiculo = ?");
        if (anio > 0) sql.append(" AND anio_fabricacion = ?");
        if (estado != null) sql.append(" AND estado = ?");

        try (Connection con = getConexion();
             PreparedStatement pst = con.prepareStatement(sql.toString())) {

            int idx = 1;

            if (!placa.isEmpty()) pst.setString(idx++, "%" + placa.toLowerCase() + "%");
            if (!tipo.isEmpty()) pst.setString(idx++, tipo);
            if (anio > 0) {
            sql.append(" AND anio_fabricacion = ?");
    pst.setInt(idx++, anio);
            }
            
            if (estado != null) pst.setBoolean(idx++, estado);

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<String> obtenerTransportesDisponibles() {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT tipo_vehiculo, placa FROM transporte WHERE estado = true";
    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            String tipo = rs.getString("tipo_vehiculo");
            String placa = rs.getString("placa");
            lista.add(tipo + " - " + placa);
        }
    } catch (SQLException e) {
        e.printStackTrace();
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
    
    public ArrayList<PaqueteTuristico> obtenerPaquetesFiltrados(String destino, String hospedaje, String transporte, String fechaIni, String fechaFin, int duracionMin) {
    ArrayList<PaqueteTuristico> lista = new ArrayList<>();

    StringBuilder sql = new StringBuilder("SELECT * FROM paquete_turistico WHERE 1=1");

    if (destino != null) sql.append(" AND destino = '").append(destino).append("'");
    if (hospedaje != null) sql.append(" AND hospedaje = '").append(hospedaje).append("'");
    if (transporte != null) sql.append(" AND transporte_placa = '").append(transporte).append("'");
    if (fechaIni != null && fechaFin != null)
        sql.append(" AND fecha_inicio >= '").append(fechaIni).append("' AND fecha_fin <= '").append(fechaFin).append("'");
    sql.append(" AND duracion_dias >= ").append(duracionMin);

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql.toString());
         ResultSet rs = pst.executeQuery()) {

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

    } catch (SQLException e) {
        System.out.println("Error al obtener paquetes: " + e.getMessage());
    }

    return lista;
}
    
    
    public PaqueteTuristico buscarPaquetePorNombre(String nombrePaquete) {
    PaqueteTuristico paquete = null;
    String sql = "SELECT * FROM paquete_turistico WHERE nombre_paquete = ?";

    try (Connection con = getConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {

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
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar el paquete", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    return paquete;
}
    
    public void insertarPaqueteTuristico(PaqueteTuristico paquete, JComboBox<String> cmbTransporte) {
    try {
        Connection cone = getConexion();
        PreparedStatement pst = cone.prepareStatement(
            "INSERT INTO paquete_turistico (nombre_paquete, destino, precio_destino, hospedaje, transporte_placa, actividades, duracion_dias, fecha_inicio, fecha_fin, precio_total) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        pst.setString(1, paquete.getNombrePaquete());
        pst.setString(2, paquete.getDestino());
        pst.setDouble(3, paquete.getPrecioDestino());
        pst.setString(4, paquete.getHospedaje());

        // Extraer solo la placa del transporte
        String transporteSeleccionado = (String) cmbTransporte.getSelectedItem();
        String placaTransporte = null;
        if (transporteSeleccionado != null && transporteSeleccionado.contains(" - ")) {
            placaTransporte = transporteSeleccionado.split(" - ")[1];
        }
        pst.setString(5, placaTransporte);

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
    
    
    
   
}
