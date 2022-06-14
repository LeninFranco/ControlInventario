package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

public class Salida implements Serializable{
    private int idSalida;
    private String fechaSalida;
    private int cantidadSalida;
    private float costoTotal;
    private float ventaTotal;

    public float getCostoTotal() {
	return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
	this.costoTotal = costoTotal;
    }

    public float getVentaTotal() {
	return ventaTotal;
    }

    public void setVentaTotal(float ventaTotal) {
	this.ventaTotal = ventaTotal;
    }
    private int idProducto;

    public Salida() {
    }

    public int getIdSalida() {
	return idSalida;
    }

    public void setIdSalida(int idSalida) {
	this.idSalida = idSalida;
    }

    public String getFechaSalida() {
	return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
	this.fechaSalida = fechaSalida;
    }

    public int getCantidadSalida() {
	return cantidadSalida;
    }

    public void setCantidadSalida(int cantidadSalida) {
	this.cantidadSalida = cantidadSalida;
    }

    public int getIdProducto() {
	return idProducto;
    }

    public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
    }
}
