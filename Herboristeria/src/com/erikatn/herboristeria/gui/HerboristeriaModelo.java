package com.erikatn.herboristeria.gui;

import com.erikatn.herboristeria.base.Aceite;
import com.erikatn.herboristeria.base.Complemento;
import com.erikatn.herboristeria.base.Infusion;
import com.erikatn.herboristeria.base.Producto;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HerboristeriaModelo {
    private ArrayList<Producto> listaDeProductos;

    public HerboristeriaModelo() {
        this.listaDeProductos = new ArrayList<>();
    }

    public ArrayList<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    public void altaInfusion(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, double gramos) {
        Infusion nuevaInfusion = new Infusion(codigo, nombre, precio, fechaCaducicada, descripcion, stock, gramos);
        listaDeProductos.add(nuevaInfusion);
    }

    public void altaAceite(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, double mililitros) {
        Aceite nuevoAceite = new Aceite(codigo, nombre, precio, fechaCaducicada, descripcion, stock, mililitros);
        listaDeProductos.add(nuevoAceite);
    }

    public void altaComplemento(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, int capsulasPorBote) {
        Complemento nuevoComplemento = new Complemento(codigo, nombre, precio, fechaCaducicada, descripcion, stock, capsulasPorBote);
        listaDeProductos.add(nuevoComplemento);
    }
    public Producto existeProducto(String codigo){
        for(Producto producto:listaDeProductos){
            if (producto.getCodigo().equalsIgnoreCase(codigo)){
                return producto;
            }
        }
        return null;
    }

    public boolean existeCodigo(String codigo) {
        for (Producto producto : listaDeProductos) {
            if (producto.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //añado el nodo raiz
        Element raiz = documento.createElement("productos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoProducto = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Producto unProducto : listaDeProductos) {
            //añadimos dentro de productos, clas infusiones, aceiyes, complemento
            if (unProducto instanceof Infusion) {
                nodoProducto = documento.createElement("Infusion");
            } else if (unProducto instanceof Aceite) {
                nodoProducto = documento.createElement("Aceite");
            } else {
                nodoProducto = documento.createElement("Complemento");
            }
            raiz.appendChild(nodoProducto);
            //dentro de producto codigo nombre....
            nodoDatos = documento.createElement("codigo");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(unProducto.getCodigo());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("nombre");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(unProducto.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unProducto.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechacaducidad");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(unProducto.getFechaCaducicada().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("descripcion");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(unProducto.getDescripcion());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("stock");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unProducto.getStock()));
            nodoDatos.appendChild(texto);

            // el campo propio
            if (unProducto instanceof Infusion) {
                nodoDatos = documento.createElement("gramos");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Infusion) unProducto).getGramos()));
                nodoDatos.appendChild(texto);
            } else if (unProducto instanceof Aceite) {
                nodoDatos = documento.createElement("mililitros");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Aceite) unProducto).getMililitros()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos = documento.createElement("capsulasporbote");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Complemento) unProducto).getCapsulasPorBote()));
                nodoDatos.appendChild(texto);
            }
        }
        //guardi fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaDeProductos = new ArrayList<Producto>();
        Infusion nuevaInfusion = null;
        Aceite nuevoAceite = null;
        Complemento nuevoComplemento = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoProducto = (Element) listaElementos.item(i);

            if (nodoProducto.getTagName().equals("Infusion")) {
                nuevaInfusion = new Infusion();
                nuevaInfusion.setCodigo(nodoProducto.getChildNodes().item(0).getTextContent());
                nuevaInfusion.setNombre(nodoProducto.getChildNodes().item(1).getTextContent());
                nuevaInfusion.setPrecio(Double.parseDouble(nodoProducto.getChildNodes().item(2).getTextContent()));
                nuevaInfusion.setFechaCaducicada(LocalDate.parse(nodoProducto.getChildNodes().item(3).getTextContent()));
                nuevaInfusion.setDescripcion(nodoProducto.getChildNodes().item(4).getTextContent());
                nuevaInfusion.setStock(Integer.parseInt(nodoProducto.getChildNodes().item(5).getTextContent()));
                nuevaInfusion.setGramos(Double.parseDouble(nodoProducto.getChildNodes().item(6).getTextContent()));
                listaDeProductos.add(nuevaInfusion);
            } else {
                if (nodoProducto.getTagName().equals("Aceite")) {
                    nuevoAceite = new Aceite();
                    nuevoAceite.setCodigo(nodoProducto.getChildNodes().item(0).getTextContent());
                    nuevoAceite.setNombre(nodoProducto.getChildNodes().item(1).getTextContent());
                    nuevoAceite.setPrecio(Double.parseDouble(nodoProducto.getChildNodes().item(2).getTextContent()));
                    nuevoAceite.setFechaCaducicada(LocalDate.parse(nodoProducto.getChildNodes().item(3).getTextContent()));
                    nuevoAceite.setDescripcion(nodoProducto.getChildNodes().item(4).getTextContent());
                    nuevoAceite.setStock(Integer.parseInt(nodoProducto.getChildNodes().item(5).getTextContent()));
                    nuevoAceite.setMililitros(Double.parseDouble(nodoProducto.getChildNodes().item(6).getTextContent()));
                    listaDeProductos.add(nuevoAceite);
                } else {
                    if (nodoProducto.getTagName().equals("Complemento")) {
                        nuevoComplemento = new Complemento();
                        nuevoComplemento.setCodigo(nodoProducto.getChildNodes().item(0).getTextContent());
                        nuevoComplemento.setNombre(nodoProducto.getChildNodes().item(1).getTextContent());
                        nuevoComplemento.setPrecio(Double.parseDouble(nodoProducto.getChildNodes().item(2).getTextContent()));
                        nuevoComplemento.setFechaCaducicada(LocalDate.parse(nodoProducto.getChildNodes().item(3).getTextContent()));
                        nuevoComplemento.setDescripcion(nodoProducto.getChildNodes().item(4).getTextContent());
                        nuevoComplemento.setStock(Integer.parseInt(nodoProducto.getChildNodes().item(5).getTextContent()));
                        nuevoComplemento.setCapsulasPorBote(Integer.parseInt(nodoProducto.getChildNodes().item(6).getTextContent()));
                        listaDeProductos.add(nuevoComplemento);
                    }
                }
            }
        }
    }
}