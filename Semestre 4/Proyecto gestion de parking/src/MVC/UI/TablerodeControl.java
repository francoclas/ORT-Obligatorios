/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MVC.UI;

import Logica.Anomalias.Anomalia;
import MVC.Controladores.ControladorTableroControl;
import MVC.DTO.parkingDTO;
import MVC.Interfaces.VistaTableroControl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author franc
 */
public class TablerodeControl extends javax.swing.JFrame implements VistaTableroControl {
    //Prueba tabla

    //Controlador
    public ControladorTableroControl Control;
    
    /**
     * Creates new form TablerodeControl
     */
    public TablerodeControl() {
        initComponents();
        Control = new ControladorTableroControl(this);
        Control.CargarParkings();
        Control.CargarAnomalias();
        Control.CargaValores();
        
   /*     Tabla_Parking.addMouseListener(new MouseAdapter(){ 
           
         /*   @Override
            public void mouseClicked(MouseEvent Clic){
               int row = Tabla_Parking.getSelectedRow();
                if (row != -1) {
                    NombreParkingSeleccionado = Tabla_Parking.getValueAt(row, 0).toString();
                    System.out.println("Selected ID: " + NombreParkingSeleccionado);
                }
            }
        }); */
                
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Parking = new javax.swing.JTable();
        Lab_Estadias = new javax.swing.JLabel();
        Lab_Factura = new javax.swing.JLabel();
        BTN_Cartelera = new javax.swing.JButton();
        BTN_VerPrecios = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Anomalias = new javax.swing.JTable();
        CBX_Anomalia = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        BTN_CerrarControl = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero Control");

        Tabla_Parking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Parking", "Ocupadas", "Libres", "Tendencia", "Factor Demanda", "Estadias", "Multas", "SubTotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Parking.setColumnSelectionAllowed(true);
        Tabla_Parking.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla_Parking);
        Tabla_Parking.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (Tabla_Parking.getColumnModel().getColumnCount() > 0) {
            Tabla_Parking.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(2).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(3).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(4).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(5).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(6).setResizable(false);
            Tabla_Parking.getColumnModel().getColumn(7).setResizable(false);
        }

        Lab_Estadias.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Lab_Estadias.setText("Estadias: ");

        Lab_Factura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Lab_Factura.setText("Facturacion:");

        BTN_Cartelera.setText("Cartelera");

        BTN_VerPrecios.setText("Ver precios");
        BTN_VerPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerPreciosActionPerformed(evt);
            }
        });

        Tabla_Anomalias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Tabla_Anomalias.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(Tabla_Anomalias);

        CBX_Anomalia.setSelected(true);
        CBX_Anomalia.setText("Monitorear anomalias");

        BTN_CerrarControl.setText("Cerrar");
        BTN_CerrarControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CerrarControlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BTN_VerPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_Cartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Lab_Estadias)
                                .addGap(91, 91, 91)
                                .addComponent(Lab_Factura)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(BTN_CerrarControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(CBX_Anomalia)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lab_Estadias)
                    .addComponent(Lab_Factura))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_Cartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_VerPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CBX_Anomalia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_CerrarControl, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_CerrarControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CerrarControlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_CerrarControlActionPerformed

        //Guarda el id de la tabla y llama Controlador de ListPrecios
    private void BTN_VerPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerPreciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_VerPreciosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TablerodeControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablerodeControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablerodeControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablerodeControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablerodeControl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cartelera;
    private javax.swing.JButton BTN_CerrarControl;
    private javax.swing.JButton BTN_VerPrecios;
    private javax.swing.JCheckBox CBX_Anomalia;
    private javax.swing.JLabel Lab_Estadias;
    private javax.swing.JLabel Lab_Factura;
    private javax.swing.JTable Tabla_Anomalias;
    private javax.swing.JTable Tabla_Parking;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean EstaEsperando() {
        return CBX_Anomalia.isSelected();
    }

    @Override
    public void VerPrecio(String NomParking) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void VerCartelera(String NomParking) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarMensajeExitoso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(
                    this,
                    mensaje,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
    }
    @Override
    public void ListarParking(DefaultTableModel Parkings) {
        Tabla_Parking.setModel(Parkings);
    }

    @Override
    public void ListarAnomalias(DefaultTableModel Anomalias) {
        Tabla_Anomalias.setModel(Anomalias);
    }

    @Override
    public void CargarLabel(int Estadias, double Total) {
        Lab_Estadias.setText("Estadia: " + Estadias);
        Lab_Factura.setText("Facturacion: " + Total);
    }
}