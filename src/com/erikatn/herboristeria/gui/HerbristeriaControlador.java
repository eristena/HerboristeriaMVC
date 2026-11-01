package com.erikatn.herboristeria.gui;

import com.erikatn.herboristeria.base.Producto;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HerbristeriaControlador implements ActionListener, WindowListener, ListSelectionListener {
    private Ventana ventana;//acceso a visita
    private HerboristeriaModelo modelo;//accedo a datos

    public HerbristeriaControlador(Ventana ventana, HerboristeriaModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;

        addWindowListener(this);
        addActionListener(this);//
        addListSelectionListener(this);

    }
    //x mi xfavor si meto otra funcionalidad apuntar aqui nopuedo mas
    public void addActionListener(ActionListener listener){
        ventana.importarButton.addActionListener(listener);
        ventana.exportarButton.addActionListener(listener);
        ventana.nuevoButton.addActionListener(listener);
        ventana.eliminarButton.addActionListener(listener);
        ventana.infusionRadioButton.addActionListener(listener);
        ventana.aceitesRadioButton.addActionListener(listener);
        ventana.complementosRadioButton.addActionListener(listener);
    }
    public void addWindowListener(WindowListener listener){
        ventana.frame.addWindowListener(listener);
    }
    public void addListSelectionListener(ListSelectionListener listener){
        ventana.listaProductosAnanidos.addListSelectionListener(listener);
    }

    //obligatorios
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    String opcionElegida= actionEvent.getActionCommand();
    switch (opcionElegida){
        case "Nuevo":
            if (hayCampoVacio()){
                Util.mensajeError("Te has dejado alguno de los campos vacios");
            break;
            }
            if (modelo.existeCodigo(ventana.codigoTxt.getText())){
                Util.mensajeError("Ya hay un producto con este codigo");
                break;
            }
            if(ventana.infusionRadioButton.isSelected()){
                modelo.altaInfusion(ventana.codigoTxt.getText(),ventana.nombreTxt.getText(),
                        Double.parseDouble(ventana.precioTxt.getText()),ventana.fechaCadDP.getDate(),
                        ventana.descripcionTxt.getText(),Integer.parseInt(ventana.stockTxt.getText()),
                        Double.parseDouble(ventana.graMl.getText()));
            }else if(ventana.aceitesRadioButton.isSelected()){
                modelo.altaAceite(ventana.codigoTxt.getText(),ventana.nombreTxt.getText(),
                        Double.parseDouble(ventana.precioTxt.getText()), ventana.fechaCadDP.getDate(),
                        ventana.descripcionTxt.getText(),Integer.parseInt(ventana.stockTxt.getText()),
                        Double.parseDouble(ventana.graMl.getText()));
            }else{
                modelo.altaComplemento(ventana.codigoTxt.getText(),ventana.nombreTxt.getText(),
                        Double.parseDouble(ventana.precioTxt.getText()), ventana.fechaCadDP.getDate(),
                        ventana.descripcionTxt.getText(),Integer.parseInt(ventana.stockTxt.getText()),
                        Integer.parseInt(ventana.graMl.getText()));
            }

            //actualizo la lista que veo bueno y limpio que vaya desastre
            limpiarCampos();
            refrescarLista();
            break;

        case "Infusion":
            ventana.campoCambiante.setText("Gramos");
        break;
        case "Aceites":
            ventana.campoCambiante.setText("Mililitros");
        break;
        case "Complementos":
            ventana.campoCambiante.setText("Nº Capsulas");
        break;

    }
    }

    public boolean hayCampoVacio(){
        if (ventana.codigoTxt.getText().isEmpty() || ventana.nombreTxt.getText().isEmpty() ||
                ventana.precioTxt.getText().isEmpty() || ventana.fechaCadDP.getText().isEmpty() ||
                ventana.graMl.getText().isEmpty() ||ventana.stockTxt.getText().isEmpty()||
                ventana.descripcionTxt.getText().isEmpty()){
            return true;
        }
        return false;
    }
    public void limpiarCampos(){
        ventana.codigoTxt.setText(null);
        ventana.nombreTxt.setText(null);
        ventana.precioTxt.setText(null);
        ventana.fechaCadDP.setText(null);
        ventana.graMl.setText(null);
        ventana.stockTxt.setText(null);
        ventana.descripcionTxt.setText(null);
        ventana.codigoTxt.requestFocus();
    }
    public void refrescarLista(){
        ventana.dlmProducto.clear();
        for (Producto productos:modelo.getListaDeProductos()) {
            ventana.dlmProducto.addElement(productos);
        }

    }

    //obligatorio por importaciones.
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
    int respuesta= Util.mensajeConfirmacionSalida("Seguro que desa salir???", "Salir");
    if(respuesta== JOptionPane.OK_OPTION){
        System.exit(0);
    }
    }
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }


}
