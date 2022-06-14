package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Salida;
import java.io.Serializable;

public class SalidaDTO implements Serializable{
    private Salida entidad;

    public SalidaDTO() {
	this.entidad = new Salida();
    }

    public Salida getEntidad() {
	return entidad;
    }

    public void setEntidad(Salida entidad) {
	this.entidad = entidad;
    }

}
