package com.erikatn.herboristeria.base;

import java.time.LocalDate;

public class Compremento extends Producto {
    private int capsulasPorBote;
    public Compremento() {
        super();
    }

    public Compremento(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, int stock, int capsulasPorBote) {
        super(codigo, nombre, precio, fechaCaducicada, descripcion, stock);
        this.capsulasPorBote = capsulasPorBote;
    }

    public int getCapsulasPorBote() {
        return capsulasPorBote;
    }

    public void setCapsulasPorBote(int capsulasPorBote) {
        this.capsulasPorBote = capsulasPorBote;
    }

    @Override
    public String toString() {
        return "Complemento " + getNombre()+ ", " + getCodigo() +", " + getPrecio() + "€";
    }
}
