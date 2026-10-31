package com.erikatn.herboristeria.gui;

import com.erikatn.herboristeria.base.Compremento;
import com.erikatn.herboristeria.base.Infusion;
import com.erikatn.herboristeria.base.Producto;

import java.time.LocalDate;
import java.util.ArrayList;

public class HerboristeriaModelo {
    private ArrayList<Producto>listaDeProductos;

    public HerboristeriaModelo() {
        this.listaDeProductos = new ArrayList<>();
    }

    public ArrayList<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    public void altaInfusion(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, double gramos){
        Infusion nuevaInfusion=new Infusion(codigo,nombre,precio,fechaCaducicada,descripcion,stock,gramos);
        listaDeProductos.add(nuevaInfusion);
    }
    public void altaAceite(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, double mililitros){
        Infusion nuevoAceite=new Infusion(codigo,nombre,precio,fechaCaducicada,descripcion,stock,mililitros);
        listaDeProductos.add(nuevoAceite);
    }
    public void altaComplemento(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, int capsulasPorBote){
        Compremento nuevoComplemento= new Compremento(codigo,nombre,precio,fechaCaducicada,descripcion,stock,capsulasPorBote);
        listaDeProductos.add(nuevoComplemento);
    }

    public boolean existeCodigo(int codigo){
        for (Producto producto:listaDeProductos) {
            if (producto.getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }
}
