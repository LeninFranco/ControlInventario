package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

public class Entrada implements Serializable{
    private int idEntrada;
    private String fechaEntrada;
    private int cantidadEntrada;
    private int idProducto;

    public Entrada() {
    }

    public int getIdEntrada() {
	return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
	this.idEntrada = idEntrada;
    }

    public String getFechaEntrada() {
	return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
	this.fechaEntrada = fechaEntrada;
    }

    public int getCantidadEntrada() {
	return cantidadEntrada;
    }

    public void setCantidadEntrada(int cantidadEntrada) {
	this.cantidadEntrada = cantidadEntrada;
    }

    public int getIdProducto() {
	return idProducto;
    }

    public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
    }
}
