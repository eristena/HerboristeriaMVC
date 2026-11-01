package com.erikatn.herboristeria.base;

import java.time.LocalDate;

public class Infusion extends Producto {
    private double gramos;

    public Infusion(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion,
                    int stock, double gramos) {
        super(codigo, nombre, precio, fechaCaducicada, descripcion, stock);
        this.gramos = gramos;
    }

    public Infusion() {
        super();
    }

    public double getGramos() {
        return gramos;
    }

    public void setGramos(double gramos) {
        this.gramos = gramos;
    }

    @Override
    public String toString() {
        return "Infusion " + getNombre()+ ", " + getCodigo() +", " + getPrecio() + "€";
    }
}

