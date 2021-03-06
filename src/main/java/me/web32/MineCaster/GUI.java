/*
 * Copyright 2013 Maximilian Soellner. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package me.web32.MineCaster;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author web32
 */
public class GUI extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
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
        scheduleMessageButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageTable = new javax.swing.JTable();
        updateLabel = new javax.swing.JLabel();

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

        scheduleMessageButton.setText("Schedule Message");
        scheduleMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleMessageButtonActionPerformed(evt);
            }
        });

        messageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(messageTable);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(0, 12, Short.MAX_VALUE)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, addGraphButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, removeGraphButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, graphSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, addMessageButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, removeMessageButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, scheduleMessageButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator2)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(configurationText)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(standardSettingsLabel))
                            .add(graphLabel))
                        .add(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 570, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 370, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(configurationText)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(standardSettingsLabel)
                        .add(8, 8, 8)
                        .add(addMessageButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(scheduleMessageButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(removeMessageButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(1, 1, 1)
                        .add(graphLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(graphSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(addGraphButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(removeGraphButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Messages", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(updateLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 275, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(27, 27, 27)
                        .add(updateLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 428, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        tabbedPane.getAccessibleContext().setAccessibleName("Allgemein");
        tabbedPane.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMessageButtonActionPerformed
        String message = JOptionPane.showInputDialog("Please insert the message text.");
        while(message.isEmpty()) {
            message = JOptionPane.showInputDialog("Your message was empty! \n Insert a new one.");
        }       
        model.addRow(new Object[] {message , "Announced every " + Main.interval + " seconds."});
        saveTable();
    }//GEN-LAST:event_addMessageButtonActionPerformed

    private void removeGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGraphButtonActionPerformed
        int selected = messageTable.getSelectedRow();
        Main.removeMessage(String.valueOf(messageTable.getValueAt(selected, 0)));
        model.removeRow(selected);
        saveTable();       
    }//GEN-LAST:event_removeGraphButtonActionPerformed

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        if(tabbedPane.getSelectedIndex() == 1) {
            model = new DefaultTableModel(new String[]{"MessageText","Scheduled at specific time"}, 0);
            for (int i = 0; i < Main.messages.size(); i++) {
                model.addRow(new Object[]{Main.messages.get(i).getMessageText(), "Announced every " + Main.interval + " seconds."});
            }
            for (int i = 0; i < Main.realTimeMessages.size(); i++) {
                model.addRow(new Object[]{Main.realTimeMessages.get(i).getMessageText(),Main.realTimeMessages.get(i).getRealTime()});
            }
            messageTable.setModel(model);
        } else {
            activatedCheckBox.setSelected(Main.enabled);
            randomCheckBox.setSelected(Main.random);
            intervalTextField.setText(String.valueOf(Main.interval));
            serverPrefixTextField.setText(Main.prefix.getMessageText());
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
        Main.random = randomCheckBox.isSelected();
        Main.interval = Integer.valueOf(intervalTextField.getText());
        Main.prefix.resetMessageText(serverPrefixTextField.getText());
        
        Main.xml.saveSettings("plugins/MineCaster/config.xml");
    }//GEN-LAST:event_saveButtonActionPerformed

    private void removeMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMessageButtonActionPerformed
        int selected = messageTable.getSelectedRow();
        Main.removeMessage(String.valueOf(messageTable.getValueAt(selected, 0)));
        model.removeRow(selected);
        saveTable();
    }//GEN-LAST:event_removeMessageButtonActionPerformed

    private void addGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGraphButtonActionPerformed
        model.addRow(new Object[] {"<PlayerCount>","Announced every " + Main.interval + " seconds."});
        saveTable();
    }//GEN-LAST:event_addGraphButtonActionPerformed

    private void scheduleMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleMessageButtonActionPerformed
        int selected = messageTable.getSelectedRow();
        String time = JOptionPane.showInputDialog("Please insert when the message should be announced (e.g. 17:25).\n The format is HH:mm and the day has 24 hours.");
        if(!time.contains(":")) {
            JOptionPane.showMessageDialog(rootPane, "The message format is HH:mm (e.g. 09:25)");
        }
        model.setValueAt(JOptionPane.showInputDialog("Please insert when the message should be announced (e.g. 17:25).\n The format is HH:mm and the day has 24 hours."), selected, 1);    
        saveTable();
    }//GEN-LAST:event_scheduleMessageButtonActionPerformed
    
    private void saveTable() {
        try {
            Main.xml.resetMessages("plugins/MineCaster/config.xml", model);
        } catch (TransformerException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Check for plugin updates
    private void checkUpdates() {
        try {
            URL url = new URL("http://46.16.219.104:25570/job/MineCaster/api/xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url.openStream());
            
            Node lastSuccBuild = doc.getElementsByTagName("latestStableBuild").item(0);
            
            
        } catch (SAXException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable messageTable;
    private javax.swing.JLabel prefixLabel;
    private javax.swing.JCheckBox randomCheckBox;
    private javax.swing.JButton removeGraphButton;
    private javax.swing.JButton removeMessageButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton scheduleMessageButton;
    private javax.swing.JTextField serverPrefixTextField;
    private javax.swing.JLabel standardSettingsLabel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel updateLabel;
    // End of variables declaration//GEN-END:variables
}
