package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

public class Producto implements Serializable{
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private float costoUnitario;
    private float precioUnitario;
    private int idCategoria;

    public Producto() {
    }

    public int getIdProducto() {
	return idProducto;
    }

    public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
    }

    public String getNombreProducto() {
	return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
	this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
	return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
	this.descripcionProducto = descripcionProducto;
    }

    public float getCostoUnitario() {
	return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
	this.costoUnitario = costoUnitario;
    }

    public float getPrecioUnitario() {
	return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
	this.precioUnitario = precioUnitario;
    }

    public int getIdCategoria() {
	return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
	this.idCategoria = idCategoria;
    }
}
