package com.erikatn.herboristeria.base;

import java.time.LocalDate;

public class Producto {
    String codigo;
    String nombre;
    double precio;
    LocalDate fechaCaducicada;
    String descripcion;
    int stock;


    public Producto(String codigo, String nombre, double precio, LocalDate fechaCaducicada, String descripcion,int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCaducicada = fechaCaducicada;
        this.descripcion = descripcion;
        this.stock= stock;
    }
    public Producto() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCaducicada() {
        return fechaCaducicada;
    }

    public void setFechaCaducicada(LocalDate fechaCaducicada) {
        this.fechaCaducicada = fechaCaducicada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        descripcion = descripcion;
    }
}
