package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;

public class ProductoDTO implements Serializable{
    private Producto entidad;

    public ProductoDTO() {
	this.entidad = new Producto();
    }

    public Producto getEntidad() {
	return entidad;
    }

    public void setEntidad(Producto entidad) {
	this.entidad = entidad;
    }
    
}
