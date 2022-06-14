package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Inventario;
import com.ipn.mx.utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventarioDAO {
    private static final String SQL_SELECT_ALL = "{CALL getInventario()}";
    private static final String SQL_SELECT_ONE = "{CALL getInventarioUno(?)}";
    
    public ArrayList<Inventario> selectAll() throws SQLException{
	ArrayList<Inventario> lista = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ALL);
	    rs = ps.executeQuery();
	    while(rs.next()){
		Inventario inv = new Inventario();
		inv.setIdProducto(rs.getInt(1));
		inv.setNombreCategoria(rs.getString(2));
		inv.setCantidadActual(rs.getInt(3));
		lista.add(inv);
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return lista;
    }
    
    public Inventario selectOne(int id) throws SQLException{
	Inventario inv = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ONE);
	    ps.setInt(1, id);
	    rs = ps.executeQuery();
	    if(rs.next()){
		inv = new Inventario();
		inv.setIdProducto(rs.getInt(1));
		inv.setNombreCategoria(rs.getString(2));
		inv.setCantidadActual(rs.getInt(3));
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return inv;
    }
}
