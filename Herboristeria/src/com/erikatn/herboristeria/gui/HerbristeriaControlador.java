package com.erikatn.herboristeria.gui;

import util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HerbristeriaControlador implements ActionListener, WindowListener {
    private Ventana ventana;//acceso a visita
    private HerboristeriaModelo modelo;//accedo a datos

    public HerbristeriaControlador(Ventana ventana, HerboristeriaModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;

        addWindowListener(this);
        addActionListener(this);//

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

    //obligatorios
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    String opcionElegida= actionEvent.getActionCommand();
    switch (opcionElegida){

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
