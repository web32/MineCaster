/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bukkit.*;

/**
 *
 * @author web32
 */
public class GUI extends javax.swing.JFrame {
    DefaultTableModel model;

    static void main() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        configurationGeneralText = new javax.swing.JLabel();
        randomCheckBox = new javax.swing.JCheckBox();
        activatedCheckBox = new javax.swing.JCheckBox();
        intervalTextField = new javax.swing.JTextField();
        intervalLabel = new javax.swing.JLabel();
        prefixLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        serverPrefixTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageTable = new javax.swing.JTable();
        addMessageButton = new javax.swing.JButton();
        removeGraphButton = new javax.swing.JButton();
        configurationText = new javax.swing.JLabel();
        graphSelection = new javax.swing.JComboBox();
        standardSettingsLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        removeMessageButton = new javax.swing.JButton();
        graphLabel = new javax.swing.JLabel();
        addGraphButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        jPasswordField1.setText("jPasswordField1");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/me/web32/MineCaster/ressources/Logo@40.png"))); // NOI18N

        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        configurationGeneralText.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        configurationGeneralText.setText("Configuration:");

        randomCheckBox.setText("Random Announcement Order");
        randomCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomCheckBoxActionPerformed(evt);
            }
        });

        activatedCheckBox.setText("Activated");

        intervalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalTextFieldActionPerformed(evt);
            }
        });

        intervalLabel.setText("Interval");

        prefixLabel.setText("Announcement Prefix");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(configurationGeneralText))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(46, 46, 46)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(activatedCheckBox)
                            .add(randomCheckBox)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(intervalTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(intervalLabel))
                                    .add(serverPrefixTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(prefixLabel))
                            .add(saveButton))))
                .add(445, 445, 445))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(configurationGeneralText)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(activatedCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(randomCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(intervalTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(intervalLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(prefixLabel)
                    .add(serverPrefixTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(38, 38, 38)
                .add(saveButton)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        tabbedPane.addTab("General", jPanel1);

        messageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Text"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(messageTable);

        addMessageButton.setText("Add Message");
        addMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMessageButtonActionPerformed(evt);
            }
        });

        removeGraphButton.setText("Remove Graph");
        removeGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGraphButtonActionPerformed(evt);
            }
        });

        configurationText.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        configurationText.setText("Configuration:");

        graphSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PlayerCount Graph" }));

        standardSettingsLabel.setText("Standard Messages:");

        removeMessageButton.setText("Remove Message");
        removeMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMessageButtonActionPerformed(evt);
            }
        });

        graphLabel.setText("Graphing System:");

        addGraphButton.setText("Add Graph");
        addGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGraphButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator2)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator1)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(6, 6, 6)
                                        .add(standardSettingsLabel))
                                    .add(configurationText)
                                    .add(graphLabel))
                                .add(0, 0, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, addMessageButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, removeMessageButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, addGraphButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, removeGraphButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, graphSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 570, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(configurationText)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(standardSettingsLabel)
                        .add(8, 8, 8)
                        .add(addMessageButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(removeMessageButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(graphLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(graphSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(8, 8, 8)
                        .add(addGraphButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(removeGraphButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Messages", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .add(0, 0, Short.MAX_VALUE))
            .add(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 428, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        tabbedPane.getAccessibleContext().setAccessibleName("Allgemein");
        tabbedPane.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMessageButtonActionPerformed
        model.addRow(new Object[] { JOptionPane.showInputDialog("Please insert the message text.")});
    }//GEN-LAST:event_addMessageButtonActionPerformed

    private void removeGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGraphButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeGraphButtonActionPerformed

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        if(tabbedPane.getSelectedIndex() == 1) {
            model = new DefaultTableModel(new String[]{"MessageText","Scheduled"}, 0);
            for (int i = 0; i < Main.messages.length; i++) {
                model.addRow(new Object[]{Main.messages[i].text});
            }
            messageTable.setModel(model);
        } else {
            activatedCheckBox.setSelected(Main.enabled);
            randomCheckBox.setSelected(Main.Random);
            intervalTextField.setText(String.valueOf(Main.interval));
            serverPrefixTextField.setText(Main.AnnouncerPrefix.text);
        }
    }//GEN-LAST:event_tabbedPaneStateChanged

    private void randomCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_randomCheckBoxActionPerformed

    private void intervalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intervalTextFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        Main.enabled = activatedCheckBox.isSelected();
        Main.Random = randomCheckBox.isSelected();
        Main.interval = Integer.valueOf(intervalTextField.getText());
        Main.AnnouncerPrefix.text = serverPrefixTextField.getText();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mc save");
    }//GEN-LAST:event_saveButtonActionPerformed

    private void removeMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMessageButtonActionPerformed
        model.removeRow(messageTable.getSelectedRow());
    }//GEN-LAST:event_removeMessageButtonActionPerformed

    private void addGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGraphButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addGraphButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activatedCheckBox;
    private javax.swing.JButton addGraphButton;
    private javax.swing.JButton addMessageButton;
    private javax.swing.JLabel configurationGeneralText;
    private javax.swing.JLabel configurationText;
    private javax.swing.JLabel graphLabel;
    private javax.swing.JComboBox graphSelection;
    private javax.swing.JLabel intervalLabel;
    private javax.swing.JTextField intervalTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable messageTable;
    private javax.swing.JLabel prefixLabel;
    private javax.swing.JCheckBox randomCheckBox;
    private javax.swing.JButton removeGraphButton;
    private javax.swing.JButton removeMessageButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField serverPrefixTextField;
    private javax.swing.JLabel standardSettingsLabel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
