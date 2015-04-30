/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author nelsonaph
 */
public class VistaCliente extends javax.swing.JFrame {

    /**
     * Creates new form VistaCliente
     */
    
    String idPizza
            , idCarne
            , idSalsa
            , idVegetal;
    
    public VistaCliente() {
        initComponents();
        //->for-Lista para Pizza
        //ComboBase.addItem("");
        
        //for
        //ComboCarne.addItem("");
        
        //for
        //ComboVegetal.addItem("");
        
        //for
        //ComboSalsa.addItem("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcono = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ComboBase = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        ComboCarne = new javax.swing.JComboBox();
        ComboVegetal = new javax.swing.JComboBox();
        ComboSalsa = new javax.swing.JComboBox();
        carnefoto = new javax.swing.JLabel();
        vegetalfoto = new javax.swing.JLabel();
        salsafoto = new javax.swing.JLabel();
        pizzafoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pizzeria/Pizza-icon.png"))); // NOI18N

        lbTitulo.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        lbTitulo.setText("Pizzeria - Cliente");

        jButton1.setText("REALIZAR PEDIDO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ComboBase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBase.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pizza Base", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 15))); // NOI18N
        ComboBase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBaseItemStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Ingredientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu Condensed", 0, 15))); // NOI18N

        ComboCarne.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboCarne.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciona Carne", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu Light", 0, 12))); // NOI18N
        ComboCarne.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboCarneItemStateChanged(evt);
            }
        });

        ComboVegetal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboVegetal.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione Vegetal"));
        ComboVegetal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboVegetalItemStateChanged(evt);
            }
        });

        ComboSalsa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboSalsa.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione Salsa"));
        ComboSalsa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboSalsaItemStateChanged(evt);
            }
        });

        carnefoto.setText("Sin Imagen");
        carnefoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        vegetalfoto.setText("Sin Imagen");
        vegetalfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        salsafoto.setText("Sin Imagen");
        salsafoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carnefoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboVegetal, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vegetalfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salsafoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSalsa, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboVegetal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSalsa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carnefoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salsafoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vegetalfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pizzafoto.setText("Sin Imagen");
        pizzafoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbIcono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ComboBase, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(pizzafoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIcono)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbTitulo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBase, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pizzafoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBaseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBaseItemStateChanged
        //**COMBO BASE**//
        String id=evt.getItem().toString();
        //pizzafoto.setText(id.substring(0, 3));
        //foto=bits;
        byte [] foto = {0};
        Image  img;
        try {
        img = ConvertirImagen(foto);                
        Icon icon = new ImageIcon( img.getScaledInstance((int)144,(int)144, Image.SCALE_AREA_AVERAGING) );            
        pizzafoto.setText("");
        pizzafoto.setIcon( icon  );
        }catch (IOException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        idPizza=id;
    }
        
    private Image ConvertirImagen(byte[] bytes) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");    
    ImageReader reader = (ImageReader) readers.next();
    Object source = bis;
    ImageInputStream iis = ImageIO.createImageInputStream(source);
    reader.setInput(iis, true);
    ImageReadParam param = reader.getDefaultReadParam();
    return reader.read(0, param);
    
        
    }//GEN-LAST:event_ComboBaseItemStateChanged

    private void ComboCarneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboCarneItemStateChanged
        //**COMBO Carne**//
        String id=evt.getItem().toString();
        //pizzafoto.setText(id.substring(0, 3));
        //foto=bits;
        byte [] foto = {0};
        Image  img;
        try {
        img = ConvertirImagen(foto);                
        Icon icon = new ImageIcon( img.getScaledInstance((int)144,(int)144, Image.SCALE_AREA_AVERAGING) );            
        carnefoto.setText("");
        carnefoto.setIcon( icon  );
        }catch (IOException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        idCarne=id;
    }//GEN-LAST:event_ComboCarneItemStateChanged

    private void ComboVegetalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboVegetalItemStateChanged
        //**COMBO BASE**//
        String id=evt.getItem().toString();
        //pizzafoto.setText(id.substring(0, 3));
        //foto=bits;
        byte [] foto = {0};
        Image  img;
        try {
        img = ConvertirImagen(foto);                
        Icon icon = new ImageIcon( img.getScaledInstance((int)144,(int)144, Image.SCALE_AREA_AVERAGING) );            
        vegetalfoto.setText("");
        vegetalfoto.setIcon( icon  );
        }catch (IOException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        idVegetal=id;
    }//GEN-LAST:event_ComboVegetalItemStateChanged

    private void ComboSalsaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboSalsaItemStateChanged
        //**COMBO BASE**//
        String id=evt.getItem().toString();
        //pizzafoto.setText(id.substring(0, 3));
        //foto=bits;
        byte [] foto = {0};
        Image  img;
        try {
        img = ConvertirImagen(foto);                
        Icon icon = new ImageIcon( img.getScaledInstance((int)144,(int)144, Image.SCALE_AREA_AVERAGING) );            
        salsafoto.setText("");
        salsafoto.setIcon( icon  );
        }catch (IOException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        idSalsa=id;
    }//GEN-LAST:event_ComboSalsaItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //llamo metodo con Todos los datos idSalsa, idCarne..
            
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBase;
    private javax.swing.JComboBox ComboCarne;
    private javax.swing.JComboBox ComboSalsa;
    private javax.swing.JComboBox ComboVegetal;
    private javax.swing.JLabel carnefoto;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIcono;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel pizzafoto;
    private javax.swing.JLabel salsafoto;
    private javax.swing.JLabel vegetalfoto;
    // End of variables declaration//GEN-END:variables
}
