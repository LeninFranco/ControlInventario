package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Entrada;
import java.io.Serializable;

public class EntradaDTO implements Serializable{
    private Entrada entidad;

    public EntradaDTO() {
	this.entidad = new Entrada();
    }

    public Entrada getEntidad() {
	return entidad;
    }

    public void setEntidad(Entrada entidad) {
	this.entidad = entidad;
    }

}
