package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.utilidades.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
    private static final String SQL_CREATE = "{CALL addCategoria(?)}";
    private static final String SQL_UPDATE = "{CALL updateCategoria(?,?)}";
    private static final String SQL_DELETE = "{CALL deleteCategoria(?)}";
    private static final String SQL_SELECT_ALL = "SELECT * FROM categoria";
    private static final String SQL_SELECT_ONE = "SELECT * FROM categoria WHERE idCategoria = ?";

    public CategoriaDAO() {
    }
    
    public void create(CategoriaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try {
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_CREATE);
	    cs.setString(1,dto.getEntidad().getNombreCategoria());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public void update(CategoriaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try{
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_UPDATE);
	    cs.setInt(1, dto.getEntidad().getIdCategoria());
	    cs.setString(2, dto.getEntidad().getNombreCategoria());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public void delete(CategoriaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try{
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_DELETE);
	    cs.setInt(1, dto.getEntidad().getIdCategoria());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public CategoriaDTO selectOne(CategoriaDTO dto) throws  SQLException{
	CategoriaDTO dtoR = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ONE);
	    ps.setInt(1, dto.getEntidad().getIdCategoria());
	    rs = ps.executeQuery();
	    if(rs.next()){
		dtoR = new CategoriaDTO();
		dtoR.getEntidad().setIdCategoria(rs.getInt(1));
		dtoR.getEntidad().setNombreCategoria(rs.getString(2));
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return dtoR;
    }
    
    public ArrayList<CategoriaDTO> selectAll() throws SQLException{
	ArrayList<CategoriaDTO> lista = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ALL);
	    rs = ps.executeQuery();
	    while(rs.next()){
		CategoriaDTO dto = new CategoriaDTO();
		dto.getEntidad().setIdCategoria(rs.getInt(1));
		dto.getEntidad().setNombreCategoria(rs.getString(2));
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
