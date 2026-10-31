package util;

import javax.swing.*;

public class Util {
    public static int mensajeConfirmacionSalida(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,
                titulo,JOptionPane.YES_NO_OPTION);
    }

}
