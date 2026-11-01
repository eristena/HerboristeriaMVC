package com.erikatn.herboristeria;

import com.erikatn.herboristeria.gui.HerboristeriaModelo;
import com.erikatn.herboristeria.gui.HerbristeriaControlador;
import com.erikatn.herboristeria.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
       Ventana ventana= new Ventana();
        HerboristeriaModelo modelo=new HerboristeriaModelo();
        HerbristeriaControlador controlador=new HerbristeriaControlador(ventana, modelo);

    }
}
