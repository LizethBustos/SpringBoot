/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.ModDTO;
import View.JFrame;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel; // INTERACTUAR con BD

/**
 *
 * @author Usuario
 */
public class controladorModDTO implements ActionListener {

    //Instancias
    ModDTO usuario = new ModDTO();
    DAO dao = new DAO();
    JFrame vista = new JFrame();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    
    //variables globales
    private int cedula;
    private String nombre;
    private String apellido;
    private String correo;
    
    public controladorModDTO(JFrame vista) {
        this.vista = vista;
        vista.setVisible(true); //para que vista se muestre
        agragarEventos();
        listarTabla();
        
    }

    private void agragarEventos() {
        vista.getBtnC().addActionListener(this);
        vista.getBtnU().addActionListener(this);
        vista.getBtnD().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);
        vista.getTblTabla().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                llenarCampos(e);
            }

        });
    }
    
    private void listarTabla(){
        String[] titulos = new String[]{"Cédula", "Nombre", "Apellido", "Correo"};
        modeloTabla = new DefaultTableModel(titulos,0);
        List<ModDTO> listausuarios = dao.listar();
        for (ModDTO usuario : listausuarios) {
            modeloTabla.addRow(new Object[]{usuario.getCedula(), usuario.getNombre(), usuario.getApellido(), usuario.getCorreo()});
 
        }
        vista.getTblTabla().setModel(modeloTabla);
        vista.getTblTabla().setPreferredSize(new Dimension(350, modeloTabla.getRowCount()*16));
    }
    
    private void llenarCampos(MouseEvent e){
        JTable target=(JTable) e.getSource();
        vista.getTxtCedula().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(),0).toString());
        vista.getTxtNombre().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(),1).toString());
        vista.getTxtApellido().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(),2).toString());
        vista.getTxtCorreo().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(),3).toString());
    }
    

    //--------------------validar formulario
    
    
    private boolean ValidarDatos(){
        if("".equals(vista.getTxtCedula().getText()) ||  "".equals(vista.getTxtNombre().getText()) ||  "".equals(vista.getTxtApellido().getText())  ||  "".equals(vista.getTxtCorreo().getText())  ){
            JOptionPane.showConfirmDialog(null, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }    
     //metodo 3 en 1
    private boolean cargarDatos(){
        try {
              cedula = Integer.parseInt(vista.getTxtCedula().getText());
              nombre = vista.getTxtNombre().getText();
              apellido = vista.getTxtApellido().getText();
              correo = vista.getTxtCorreo().getText();
              return true;    
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(null, "El campo Cédula debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar datos:" + e);
            return false;
        }
    }
    
    private void limpiarCampos(){
        vista.getTxtCedula().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtApellido().setText("");
        vista.getTxtCorreo().setText("");
        cedula=0;
        nombre="";
        apellido="";
        correo="";
 }
    //---------------------------------------
      
    private void agregarUsuarios(){
        try {
            if (ValidarDatos()) {
                if (cargarDatos()) {
                    ModDTO usuario = new ModDTO(cedula, nombre, apellido, correo);
                    dao.agregar(usuario);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    limpiarCampos();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error agregar:" + e);
        }finally{
            listarTabla();
        }
    }
   
    
    private void actualizarUsuario(){
        try {
            if(ValidarDatos()){
                if(cargarDatos()){
                   ModDTO usuario = new ModDTO(cedula, nombre, apellido, correo);
                   dao.actualizar(usuario);
                   JOptionPane.showMessageDialog(null, "Registro actualizado");
                   limpiarCampos();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error al actualizarC:" + e);
        }finally{
            listarTabla();
        }
    
    }
    
    private void borrarusuario(){
        try {
            if(ValidarDatos()){
                if(cargarDatos()){
                   ModDTO usuario = new ModDTO(cedula, nombre, apellido, correo); 
                   dao.borrar(cedula);
                   JOptionPane.showMessageDialog(null, "Usuario Borrado");
                   limpiarCampos();
                }
             }
        } catch (HeadlessException e) {
            JOptionPane.showConfirmDialog(null, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al borrarC:" + e);
        }finally{
            listarTabla();
        }
    }
    
    
    //da acciones a botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== vista.getBtnC()) {
            agregarUsuarios();
        }
        if (e.getSource()== vista.getBtnU()) {
            actualizarUsuario();
        }
        if (e.getSource()== vista.getBtnD()) {
            borrarusuario();
        }
        if (e.getSource()== vista.getBtnLimpiar()) {
            limpiarCampos();
        }

    }  

}//final class controladorModDTO



    
    
    


