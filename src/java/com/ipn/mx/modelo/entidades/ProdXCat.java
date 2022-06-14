package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

public class ProdXCat implements Serializable{
    
    private String nombreCategoria;
    private int cantidad;

    public ProdXCat() {
    }

    public String getNombreCategoria() {
	return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
	this.nombreCategoria = nombreCategoria;
    }

    public int getCantidad() {
	return cantidad;
    }

    public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
    }
    
}
