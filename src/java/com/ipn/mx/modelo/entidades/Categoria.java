package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

public class Categoria implements Serializable{
    private int idCategoria;
    private String nombreCategoria;

    public Categoria() {
    }

    public int getIdCategoria() {
	return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
	this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
	return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
	this.nombreCategoria = nombreCategoria;
    }
}
