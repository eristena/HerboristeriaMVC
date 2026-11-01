package com.erikatn.herboristeria.base;

import java.time.LocalDate;

public class Aceite extends Producto {
    private double mililitros;


    public Aceite(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion,
                  int stock, double mililitros) {
        super(codigo, nombre, precio, fechaCaducicada, descripcion, stock);
        this.mililitros = mililitros;
    }

    public Aceite() {
        super();
    }

    public double getMililitros() {
        return mililitros;
    }

    public void setMililitros(double mililitros) {
        this.mililitros = mililitros;
    }

    @Override
    public String toString() {
        return "Aceite " + getNombre()+ ", " + getCodigo() +", " + getPrecio() + "€";
    }

}
