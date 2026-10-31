package com.erikatn.herboristeria.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HerbristeriaControlador implements ActionListener {
    private Ventana ventana;//acceso a visita
    private HerboristeriaModelo modelo;//accedo a datos

    public HerbristeriaControlador(Ventana ventana, HerboristeriaModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;


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
}
