package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.EntradaDTO;
import com.ipn.mx.utilidades.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntradaDAO {
    private static final String SQL_CREATE = "{CALL addEntrada(?,?)}";
    private static final String SQL_UPDATE = "{CALL updateEntrada(?,?,?)}";
    private static final String SQL_SELECT_ALL = "SELECT * FROM entrada";
    private static final String SQL_SELECT_ONE = "SELECT * FROM entrada WHERE idEntrada=?";
    
    public EntradaDAO() {
    }
    
    public void create(EntradaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try {
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_CREATE);
	    cs.setInt(1, dto.getEntidad().getCantidadEntrada());
	    cs.setInt(2, dto.getEntidad().getIdProducto());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public void update(EntradaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try{
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_UPDATE);
	    cs.setInt(1, dto.getEntidad().getIdEntrada());
	    cs.setInt(2, dto.getEntidad().getCantidadEntrada());
	    cs.setInt(3,dto.getEntidad().getIdProducto());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public EntradaDTO selectOne(EntradaDTO dto) throws SQLException{
	EntradaDTO dtoR = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ONE);
	    ps.setInt(1, dto.getEntidad().getIdEntrada());
	    rs = ps.executeQuery();
	    if(rs.next()){
		dtoR = new EntradaDTO();
		dtoR.getEntidad().setIdEntrada(rs.getInt(1));
		dtoR.getEntidad().setFechaEntrada(rs.getString(2));
		dtoR.getEntidad().setCantidadEntrada(rs.getInt(3));
		dtoR.getEntidad().setIdProducto(rs.getInt(4));
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return dtoR;
    }
    
    public ArrayList<EntradaDTO> selectAll() throws SQLException{
	ArrayList<EntradaDTO> lista = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ALL);
	    rs = ps.executeQuery();
	    while(rs.next()){
		EntradaDTO dto = new EntradaDTO();
		dto.getEntidad().setIdEntrada(rs.getInt(1));
		dto.getEntidad().setFechaEntrada(rs.getString(2));
		dto.getEntidad().setCantidadEntrada(rs.getInt(3));
		dto.getEntidad().setIdProducto(rs.getInt(4));
		lista.add(dto);
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return lista;
    }
}
