package ventanas;

import java.util.Observable;
import ventanas.filtros.ExampleFileFilter;
import java.io.File;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import warpature.Warpature;

public class VentanaPrincipal extends javax.swing.JFrame implements Observer{

    private Warpature warpature;

    public Warpature getWarpature() {
        return warpature;
    }

    public void setWarpature(Warpature warpature) {
        this.warpature = warpature;
    }

    public VentanaPrincipal() {
        initComponents();
        setWarpature(new Warpature());
        getWarpature().getProcesador().addObserver(this);
        labelImagen.addMouseListener(getWarpature().getRaton());
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        labelImagen.setVerticalAlignment(SwingConstants.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelImagen = new javax.swing.JLabel();
        textRadio = new javax.swing.JTextField();
        labelRadio = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuItemCargar = new javax.swing.JMenuItem();
        menuItemGuardar = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        menuItemVolver = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        labelImagen.setAlignmentX(0.5F);
        labelImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelImagen.setMaximumSize(new java.awt.Dimension(640, 480));
        labelImagen.setMinimumSize(new java.awt.Dimension(640, 480));
        labelImagen.setPreferredSize(new java.awt.Dimension(640, 480));

        textRadio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textRadio.setText("10");
        textRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRadioActionPerformed(evt);
            }
        });

        labelRadio.setText("Radio:");

        menuArchivo.setText("Archivo");

        menuItemCargar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemCargar.setText("Cargar");
        menuItemCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCargarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemCargar);

        menuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemGuardar.setText("Guardar");
        menuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemGuardar);

        menuBar.add(menuArchivo);

        menuEditar.setText("Editar");

        menuItemVolver.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuItemVolver.setText("Volver");
        menuItemVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVolverActionPerformed(evt);
            }
        });
        menuEditar.add(menuItemVolver);

        menuBar.add(menuEditar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textRadio, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(labelRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRadioActionPerformed
        try{
            getWarpature().getProcesador().setRadio(Double.parseDouble(textRadio.getText()));
        }catch(Exception ex){
            textRadio.setText(String.valueOf(getWarpature().getProcesador().getRadio()));
        }
    }//GEN-LAST:event_textRadioActionPerformed

    private void menuItemCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCargarActionPerformed
        JFileChooser chooser = new JFileChooser();
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("jpg");
        filter.setDescription("JPG Images");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           try{
                getWarpature().setNuevaImagen(ImageIO.read(chooser.getSelectedFile()));
            }catch(Exception ex){
            }
        }
        labelImagen.setIcon(new ImageIcon(getWarpature().getProcesador().getImagen()));
    }//GEN-LAST:event_menuItemCargarActionPerformed

    private void menuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGuardarActionPerformed
        JFileChooser chooser = new JFileChooser();
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("jpg");
        filter.setDescription("JPG Images");
        chooser.setFileFilter(filter);
        chooser.setSelectedFile(new File("warped.jpg"));
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try{
                ImageIO.write(getWarpature().getImagenFinal(),"jpg",chooser.getSelectedFile());
            }catch(Exception ex){
            }
        }
    }//GEN-LAST:event_menuItemGuardarActionPerformed

    private void menuItemVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVolverActionPerformed
        getWarpature().getProcesador().volver();
    }//GEN-LAST:event_menuItemVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelRadio;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenuItem menuItemCargar;
    private javax.swing.JMenuItem menuItemGuardar;
    private javax.swing.JMenuItem menuItemVolver;
    private javax.swing.JTextField textRadio;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        labelImagen.setIcon(new ImageIcon(getWarpature().getProcesador().getImagen()));
    }
}