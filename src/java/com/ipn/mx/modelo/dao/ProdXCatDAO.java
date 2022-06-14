package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.ProdXCat;
import com.ipn.mx.utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdXCatDAO {
    private static final String SQL_SELECT_ALL = "{CALL getProductosXCategoria()}";
    
    public ArrayList<ProdXCat> selectAll() throws SQLException{
	ArrayList<ProdXCat> lista = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ALL);
	    rs = ps.executeQuery();
	    while(rs.next()){
		ProdXCat pxc = new ProdXCat();
		pxc.setNombreCategoria(rs.getString(1));
		pxc.setCantidad(rs.getInt(2));
		lista.add(pxc);
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return lista;
    }
}
