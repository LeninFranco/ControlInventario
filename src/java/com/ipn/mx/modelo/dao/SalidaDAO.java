package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.SalidaDTO;
import com.ipn.mx.utilidades.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalidaDAO {
    private static final String SQL_CREATE = "{CALL addSalida(?,?,?,?)}";
    private static final String SQL_UPDATE = "{CALL updateSalida(?,?,?,?,?)}";
    private static final String SQL_SELECT_ALL = "SELECT * FROM salida";
    private static final String SQL_SELECT_ONE = "SELECT * FROM salida WHERE idSalida=?";
    
    public SalidaDAO() {
    }
    
    public void create(SalidaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try {
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_CREATE);
	    cs.setInt(1, dto.getEntidad().getCantidadSalida());
	    cs.setFloat(2, dto.getEntidad().getCostoTotal());
	    cs.setFloat(3, dto.getEntidad().getVentaTotal());
	    cs.setInt(4, dto.getEntidad().getIdProducto());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public void update(SalidaDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try{
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_UPDATE);
	    cs.setInt(1, dto.getEntidad().getIdSalida());
	    cs.setInt(2, dto.getEntidad().getCantidadSalida());
	    cs.setFloat(3, dto.getEntidad().getCostoTotal());
	    cs.setFloat(4, dto.getEntidad().getVentaTotal());
	    cs.setInt(5, dto.getEntidad().getIdProducto());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public SalidaDTO selectOne(SalidaDTO dto) throws SQLException{
	SalidaDTO dtoR = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ONE);
	    ps.setInt(1, dto.getEntidad().getIdSalida());
	    rs = ps.executeQuery();
	    if(rs.next()){
		dtoR = new SalidaDTO();
		dtoR.getEntidad().setIdSalida(rs.getInt(1));
		dtoR.getEntidad().setFechaSalida(rs.getString(2));
		dtoR.getEntidad().setCantidadSalida(rs.getInt(3));
		dtoR.getEntidad().setCostoTotal(rs.getFloat(4));
		dtoR.getEntidad().setVentaTotal(rs.getFloat(5));
		dtoR.getEntidad().setIdProducto(rs.getInt(6));
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return dtoR;
    }
    
    public ArrayList<SalidaDTO> selectAll() throws SQLException{
	ArrayList<SalidaDTO> lista = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ALL);
	    rs = ps.executeQuery();
	    while(rs.next()){
		SalidaDTO dto = new SalidaDTO();
		dto.getEntidad().setIdSalida(rs.getInt(1));
		dto.getEntidad().setFechaSalida(rs.getString(2));
		dto.getEntidad().setCantidadSalida(rs.getInt(3));
		dto.getEntidad().setCostoTotal(rs.getFloat(4));
		dto.getEntidad().setVentaTotal(rs.getFloat(5));
		dto.getEntidad().setIdProducto(rs.getInt(6));
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
