package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

public class Inventario implements Serializable{
    private int idProducto;
    private String nombreCategoria;
    private int cantidadActual;

    public Inventario() {
    }

    public int getIdProducto() {
	return idProducto;
    }

    public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
    }

    public String getNombreCategoria() {
	return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
	this.nombreCategoria = nombreCategoria;
    }

    public int getCantidadActual() {
	return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
	this.cantidadActual = cantidadActual;
    }
}
