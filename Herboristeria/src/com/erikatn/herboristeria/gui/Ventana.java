package com.erikatn.herboristeria.gui;

import com.erikatn.herboristeria.base.Producto;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    public JRadioButton infusionRadioButton;
    public JRadioButton aceitesRadioButton;
    public JRadioButton complementosRadioButton;

    public JTextField codigoTxt;
    public JTextField nombreTxt;
    public JTextField precioTxt;
    public DatePicker fechaCadDP;
    public JTextField stockTxt;
    public JTextArea descripcionTxt;
    public JList listaProductosAnanidos;
    public JTextField graMl;
    public JLabel campoCambiante;
    public JTextField buscarCodTxt;

    public JButton nuevoButton;
    public JButton importarButton;
    public JButton eliminarButton;
    public JButton modificarButton;
    public JButton buscarButton;
    public JButton exportarButton;
    //los creo a mio
    public JFrame frame;
    public DefaultListModel<Producto> dlmProducto;


    public Ventana(){
        frame= new JFrame("Herboristeria");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();

    }
    public void initComponents(){
        dlmProducto=new DefaultListModel<Producto>();
        listaProductosAnanidos.setModel(dlmProducto);
        // esti me sirve para asignar al buscarcodtxt la etiqueta buscar para que cuando se haga un evento pueda funcionar
        buscarCodTxt.setActionCommand("Buscar");
    }

}
