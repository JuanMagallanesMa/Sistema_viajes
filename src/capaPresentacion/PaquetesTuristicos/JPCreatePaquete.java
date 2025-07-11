/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package capaPresentacion.PaquetesTuristicos;

import capaNegocio.Controlador;
import entidades.PaqueteTuristico;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan magallanes
 */
public class JPCreatePaquete extends javax.swing.JPanel {

    /**
     * Creates new form JPCreate
     */
    public JPCreatePaquete() {
        initComponents();
        cargarPaises();
        cargarHospedajes();
        cargarActividades();
        cargarTransportes() ;
        cmbHospedaje.addActionListener(e -> calcularPrecioTotal());
        spinDuracion.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            calcularPrecioTotal();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        cmbHospedaje = new javax.swing.JComboBox<>();
        cmbTransporte = new javax.swing.JComboBox<>();
        spinDuracion = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinFechaInicio = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtPrecioTotal = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        cmbDestino = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        cmbActividades = new javax.swing.JComboBox<>();
        spinFechaFin = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(503, 404));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre del paquete"));
        txtNombre.setMinimumSize(new java.awt.Dimension(200, 50));
        txtNombre.setPreferredSize(new java.awt.Dimension(200, 50));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setPreferredSize(new java.awt.Dimension(190, 40));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cmbHospedaje.setBorder(javax.swing.BorderFactory.createTitledBorder("Hospedaje"));
        cmbHospedaje.setMinimumSize(new java.awt.Dimension(64, 39));
        cmbHospedaje.setPreferredSize(new java.awt.Dimension(200, 50));
        cmbHospedaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHospedajeActionPerformed(evt);
            }
        });

        cmbTransporte.setBorder(javax.swing.BorderFactory.createTitledBorder("Transporte"));
        cmbTransporte.setMinimumSize(new java.awt.Dimension(64, 39));
        cmbTransporte.setPreferredSize(new java.awt.Dimension(200, 50));

        spinDuracion.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinDuracion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinDuracionStateChanged(evt);
            }
        });

        jLabel1.setText("Dias");

        jLabel2.setText("Duracion");

        spinFechaInicio.setModel(new javax.swing.SpinnerDateModel());

        jLabel3.setText("Fecha de inicio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbHospedaje, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTransporte, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spinFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbHospedaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtPrecioTotal.setBorder(javax.swing.BorderFactory.createTitledBorder("Precio total"));
        txtPrecioTotal.setMinimumSize(new java.awt.Dimension(200, 50));
        txtPrecioTotal.setPreferredSize(new java.awt.Dimension(200, 50));
        txtPrecioTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioTotalActionPerformed(evt);
            }
        });

        txtPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder("Precio"));
        txtPrecio.setMinimumSize(new java.awt.Dimension(200, 50));
        txtPrecio.setPreferredSize(new java.awt.Dimension(200, 50));

        cmbDestino.setBorder(javax.swing.BorderFactory.createTitledBorder("Destino"));
        cmbDestino.setMinimumSize(new java.awt.Dimension(64, 39));
        cmbDestino.setPreferredSize(new java.awt.Dimension(200, 50));

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(null);
        btnLimpiar.setPreferredSize(new java.awt.Dimension(190, 40));

        cmbActividades.setBorder(javax.swing.BorderFactory.createTitledBorder("Actividades"));
        cmbActividades.setMinimumSize(new java.awt.Dimension(64, 39));
        cmbActividades.setPreferredSize(new java.awt.Dimension(200, 50));

        spinFechaFin.setModel(new javax.swing.SpinnerDateModel());

        jLabel4.setText("Fecha fin");

        jLabel5.setText("$");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmbActividades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtPrecio, txtPrecioTotal});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarPaqueteTuristico() ;// TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbHospedajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHospedajeActionPerformed
        // TODO add your handling code here:
         String seleccionado = (String) cmbTransporte.getSelectedItem();
if (seleccionado != null && seleccionado.contains(" - ")) {
    String placa = seleccionado.split(" - ")[1];  // toma lo que está después del " - "
    // usa esta 'placa' para guardar en la BD
}
    calcularPrecioTotal();
    
    }//GEN-LAST:event_cmbHospedajeActionPerformed

    private void txtPrecioTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioTotalActionPerformed

    private void spinDuracionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinDuracionStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spinDuracionStateChanged
    private void cargarPaises() {
        String[] paises = {"Ecuador", "Perú", "México"};

        
        cmbDestino.removeAllItems();

        for (String pais : paises) {
            
            cmbDestino.addItem(pais);
        }
    }
    
    
    

private Map<String, Double> preciosHospedaje = new HashMap<>();
private void cargarHospedajes() {
    preciosHospedaje.clear();
    cmbHospedaje.removeAllItems();

    preciosHospedaje.put("Hotel 3 estrellas", 50.0);
    preciosHospedaje.put("Hotel 5 estrellas", 120.0);
    preciosHospedaje.put("Hostal", 30.0);
    preciosHospedaje.put("Cabaña", 70.0);
    preciosHospedaje.put("Resort", 150.0);

    for (String nombre : preciosHospedaje.keySet()) {
        cmbHospedaje.addItem(nombre);
    }
}
private void calcularPrecioTotal() {
    String hospedajeSeleccionado = (String) cmbHospedaje.getSelectedItem();
    if (hospedajeSeleccionado == null) {
        System.out.println("No hay hospedaje seleccionado");
        txtPrecio.setText("");
        txtPrecioTotal.setText("");
        return;
    }
    Double precio = preciosHospedaje.get(hospedajeSeleccionado);
    System.out.println("Precio encontrado para hospedaje: " + precio);
    if (precio == null) {
        System.out.println("No se encontró precio para el hospedaje");
        txtPrecio.setText("");
        txtPrecioTotal.setText("");
        return;
    }
    // Mostrar precio unitario en txtPrecio
    txtPrecio.setText(String.format("%.2f", precio));

    int duracion = (Integer) spinDuracion.getValue();
    System.out.println("Duración: " + duracion);

    // Calcular total y mostrar en txtPrecioTotal
    double total = precio * duracion;
    txtPrecioTotal.setText(String.format("%.2f", total));
    System.out.println("Precio total seteado: " + total);
} 

private void cargarActividades() {
    cmbActividades.removeAllItems();
    cmbActividades.addItem("Tour histórico");
    cmbActividades.addItem("Caminata ecológica");
    cmbActividades.addItem("Snorkel");
    cmbActividades.addItem("Escalada");
    cmbActividades.addItem("Tour gastronómico");
}

private void cargarTransportes() {
    Controlador controlador = new Controlador();
    List<String> transportes = controlador.obtenerTransportesDisponibles();

    cmbTransporte.removeAllItems();
    for (String t : transportes) {
        cmbTransporte.addItem(t);
    }
}

private void guardarPaqueteTuristico() {
    try {
        String nombre = txtNombre.getText();
        String destino = (String) cmbDestino.getSelectedItem();
        String precioText = txtPrecio.getText().replace(",",".").trim();
        String precioTotalText = txtPrecioTotal.getText().replace(",",".").trim();
        int duracion = (Integer) spinDuracion.getValue();
        String hospedaje = (String) cmbHospedaje.getSelectedItem();
        String transporte = (String) cmbTransporte.getSelectedItem();
        String actividades = (String) cmbActividades.getSelectedItem();
        String fechaInicio = spinFechaInicio.getValue().toString();
        String fechaFin = spinFechaFin.getValue().toString();

        if (nombre.isEmpty() || destino == null || precioText.isEmpty() || precioTotalText.isEmpty() || hospedaje == null || transporte == null) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos requeridos.");
            return;
        }

        double precio = Double.parseDouble(precioText);
        double precioTotal = Double.parseDouble(precioTotalText);

        PaqueteTuristico paquete = new PaqueteTuristico();
        paquete.setNombrePaquete(nombre);
        paquete.setDestino(destino);
        paquete.setPrecioDestino(precio);
        paquete.setHospedaje(hospedaje);
        paquete.setActividades(actividades);
        paquete.setDuracionDias(duracion);

        // Extraer solo la placa del transporte
        String placaTransporte = null;
        if (transporte.contains(" - ")) {
            placaTransporte = transporte.split(" - ")[1];
        }
        paquete.setTransportePlaca(placaTransporte);

        paquete.setFechaInicio(fechaInicio);
        paquete.setFechaFin(fechaFin);
        paquete.setPrecioTotal(precioTotal);

        Controlador controlador = new Controlador();
        controlador.insertarPaqueteTuristico(paquete, cmbTransporte);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Precio o precio total inválido. Debe ingresar un número válido.");
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbActividades;
    private javax.swing.JComboBox<String> cmbDestino;
    private javax.swing.JComboBox<String> cmbHospedaje;
    private javax.swing.JComboBox<String> cmbTransporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner spinDuracion;
    private javax.swing.JSpinner spinFechaFin;
    private javax.swing.JSpinner spinFechaInicio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioTotal;
    // End of variables declaration//GEN-END:variables
}
