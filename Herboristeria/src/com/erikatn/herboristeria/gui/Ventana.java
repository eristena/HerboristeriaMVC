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
    public JTextField PrecioTxt;
    public DatePicker fechaCadDP;
    public JButton importarButton;
    public JButton exportarButton;
    public JButton nuevoButton;
    public JButton eliminarButton;
    public JTextArea descripcionTxt;
    public JList listaProductosAnanidos;
    public JTextField graMl;
    public JLabel campoCambiante;
    //los creo a malo
    public JFrame frame;
    public DefaultListModel<Producto> dlmProducto;


    public Ventana(){
        frame= new JFrame("Herboristeria");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void initComponets(){
        dlmProducto=new DefaultListModel<Producto>();
        listaProductosAnanidos.setModel(dlmProducto);
    }

}
