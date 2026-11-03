package com.erikatn.herboristeria.gui;

import com.erikatn.herboristeria.base.Aceite;
import com.erikatn.herboristeria.base.Complemento;
import com.erikatn.herboristeria.base.Infusion;
import com.erikatn.herboristeria.base.Producto;
import org.xml.sax.SAXException;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class HerbristeriaControlador implements ActionListener, WindowListener, ListSelectionListener {
    private Ventana ventana;//acceso a visita
    private HerboristeriaModelo modelo;//accedo a datos
    private File ultimaRutaExportada;

    public HerbristeriaControlador(Ventana ventana, HerboristeriaModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No es encontro el archivo "+e.getMessage());
        }

        addWindowListener(this);
        addActionListener(this);//
        addListSelectionListener(this);

    }
    //x mi xfavor si meto otra funcionalidad apuntar aqui nopuedo mas
    public void addActionListener(ActionListener listener){
        ventana.eliminarButton.addActionListener(listener);
        ventana.buscarButton.addActionListener(listener);
        ventana.exportarButton.addActionListener(listener);
        ventana.nuevoButton.addActionListener(listener);
        ventana.importarButton.addActionListener(listener);
        ventana.venderButton.addActionListener(listener);
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

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("herboristeria.conf"));
        ultimaRutaExportada=new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("herboristeria.conf"),"Datos configuracion herboristeria");
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
            try {
                if (ventana.infusionRadioButton.isSelected()) {
                    modelo.altaInfusion(ventana.codigoTxt.getText(), ventana.nombreTxt.getText(),
                            Double.parseDouble(ventana.precioTxt.getText()), ventana.fechaCadDP.getDate(),
                            ventana.descripcionTxt.getText(), Integer.parseInt(ventana.stockTxt.getText()),
                            Double.parseDouble(ventana.graMl.getText()));
                } else if (ventana.aceitesRadioButton.isSelected()) {
                    modelo.altaAceite(ventana.codigoTxt.getText(), ventana.nombreTxt.getText(),
                            Double.parseDouble(ventana.precioTxt.getText()), ventana.fechaCadDP.getDate(),
                            ventana.descripcionTxt.getText(), Integer.parseInt(ventana.stockTxt.getText()),
                            Double.parseDouble(ventana.graMl.getText()));
                } else {
                    modelo.altaComplemento(ventana.codigoTxt.getText(), ventana.nombreTxt.getText(),
                            Double.parseDouble(ventana.precioTxt.getText()), ventana.fechaCadDP.getDate(),
                            ventana.descripcionTxt.getText(), Integer.parseInt(ventana.stockTxt.getText()),
                            Integer.parseInt(ventana.graMl.getText()));
                }
            }catch (IllegalArgumentException e) {
                Util.mensajeError("Has añadido un argumento no valido, en el precio/gramos/ml hay que poner ',' no '.' y ");
            }
            limpiarCampos();
            refrescarLista();
            break;
        case "Importar":
            JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,
                    "Archivos XML","xml");
            int opt=selectorFichero.showOpenDialog(null);
            if (opt==JFileChooser.APPROVE_OPTION) {
                try {
                    modelo.importarXML(selectorFichero.getSelectedFile());
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                refrescarLista();
            }
            break;
        case "Exportar":
            JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,
                    "Archivos XML","xml");
            int opt2=selectorFichero2.showSaveDialog(null);
            if (opt2==JFileChooser.APPROVE_OPTION) {

                try {
                    modelo.exportarXML(selectorFichero2.getSelectedFile());
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
            }

            break;
        case "Eliminar":
            //cojo el inidice del producto que eliminare
            int indice = ventana.listaProductosAnanidos.getSelectedIndex();
            //si no hay nada es -1
            if (indice == -1) {
                Util.mensajeError("Selecciona un producto de la lista");
                break;
            }
            //me voy al modelo y le digo que lo quite del array
            modelo.getListaDeProductos().remove(indice);
            //me voy a dlm pra que lo quite y asi se actualice en la vista
            ventana.dlmProducto.remove(indice);
            break;
        case "Buscar":
            //compruebo que esta buscando algo
            if(ventana.buscarCodTxt.getText().isEmpty()){
                Util.mensajeError("Tienes que buscar un codigo en el apartado de buscar");
                break;
            }
            //creo el objeto de producto encontrado y con el metodo y le paso el codigo a buscar:/
            Producto productoEncontrado=modelo.existeProducto(ventana.buscarCodTxt.getText());
            if (productoEncontrado==null){
                Util.mensajeError("No existe un producto con ese codigo");
                break;
            }
            //pido la lista de productos y con eso busco la posicion de mi producto
            int posicion=modelo.getListaDeProductos().indexOf(productoEncontrado);
            //aqui voy al list y le digo que vaya a el que este en tal posicion
            ventana.listaProductosAnanidos.setSelectedIndex(posicion);

            break;


        case "Vender":
            // busco el producto por el codigo que salga al darle
            Producto productoVender = modelo.existeProducto(ventana.codigoTxt.getText());

            if (productoVender == null) {
                Util.mensajeError("No existe un producto con ese codigo");
                break;
            }
            if (productoVender.getStock() == 0) {
                Util.mensajeError("No quedan existencias de este producto");
                break;
            }
            // le resto 1 al stock y lo pongo en el cuadro de stock
            productoVender.setStock(productoVender.getStock() - 1);
            ventana.stockTxt.setText(String.valueOf(productoVender.getStock()));
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

        if (listSelectionEvent.getValueIsAdjusting() ) {

            // cojo el producto que he hecho click pero me lo devuelve como objeto literalmente

            Producto productoSeleccionado = (Producto) ventana.listaProductosAnanidos.getSelectedValue();

            if(productoSeleccionado==null){
                return;
            }

            // pongo los datos en los cuadros
            ventana.codigoTxt.setText(productoSeleccionado.getCodigo());
            ventana.nombreTxt.setText(productoSeleccionado.getNombre());

            ventana.precioTxt.setText(String.valueOf(productoSeleccionado.getPrecio()));
            // LA FECHA GET DATE QUE SINO PETA MUCHO
            ventana.fechaCadDP.setDate(productoSeleccionado.getFechaCaducicada());
            ventana.descripcionTxt.setText(productoSeleccionado.getDescripcion());
            ventana.stockTxt.setText(String.valueOf(productoSeleccionado.getStock()));
            // miro que tipo de producto es para el radiobutton, comprebo si son instancias de cada uno
            // y le digo que haga click a eso
            if (productoSeleccionado instanceof Infusion) {
                ventana.infusionRadioButton.doClick();
                //casteo el  el producto para poder usar el get propio de cada clase
                ventana.graMl.setText(String.valueOf(((Infusion) productoSeleccionado).getGramos()));
            } else if (productoSeleccionado instanceof Aceite) {
                ventana.aceitesRadioButton.doClick();
                ventana.graMl.setText(String.valueOf(((Aceite) productoSeleccionado).getMililitros()));
            } else {
                ventana.complementosRadioButton.doClick();
                ventana.graMl.setText(String.valueOf(((Complemento) productoSeleccionado).getCapsulasPorBote()));
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int respuesta= Util.mensajeConfirmacionSalida("¿Seguro que desa salir?", "Salir");
        if(respuesta== JOptionPane.OK_OPTION){
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
