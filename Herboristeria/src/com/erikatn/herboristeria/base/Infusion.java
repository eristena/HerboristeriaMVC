package com.erikatn.herboristeria.base;

import java.time.LocalDate;

public class Infusion extends Producto {
    double gramos;

    public Infusion(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion, double gramos) {
        super(codigo, nombre, precio, fechaCaducicada, descripcion);
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
        return "Infusion{" +
                "gramos=" + gramos +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", fechaCaducicada=" + fechaCaducicada +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

