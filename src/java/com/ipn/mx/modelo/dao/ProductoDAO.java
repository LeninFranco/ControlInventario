package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.utilidades.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {
    private static final String SQL_CREATE = "{CALL addProducto(?,?,?,?,?)}";
    private static final String SQL_UPDATE = "{CALL updateProducto(?,?,?,?,?,?)}";
    private static final String SQL_DELETE = "{CALL deleteProducto(?)}";
    private static final String SQL_SELECT_ALL = "SELECT * FROM producto";
    private static final String SQL_SELECT_ONE = "SELECT * FROM producto WHERE idProducto = ?";
    
    public ProductoDAO() {
    }
    
    public void create(ProductoDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try {
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_CREATE);
	    cs.setString(1,dto.getEntidad().getNombreProducto());
	    cs.setString(2,dto.getEntidad().getDescripcionProducto());
	    cs.setFloat(3, dto.getEntidad().getCostoUnitario());
	    cs.setFloat(4, dto.getEntidad().getPrecioUnitario());
	    cs.setInt(5, dto.getEntidad().getIdCategoria());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public void update(ProductoDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try{
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_UPDATE);
	    cs.setInt(1, dto.getEntidad().getIdProducto());
	    cs.setString(2,dto.getEntidad().getNombreProducto());
	    cs.setString(3,dto.getEntidad().getDescripcionProducto());
	    cs.setFloat(4, dto.getEntidad().getCostoUnitario());
	    cs.setFloat(5, dto.getEntidad().getPrecioUnitario());
	    cs.setInt(6, dto.getEntidad().getIdCategoria());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public void delete(ProductoDTO dto) throws SQLException{
	Connection conn = null;
	CallableStatement cs = null;
	try{
	    conn = ConexionDB.getConnection();
	    cs = conn.prepareCall(SQL_DELETE);
	    cs.setInt(1, dto.getEntidad().getIdProducto());
	    cs.executeUpdate();
	} finally{
	    if(cs != null) cs.close();
	    if(conn != null) conn.close();
	}
    }
    
    public ProductoDTO selectOne(ProductoDTO dto) throws  SQLException{
	ProductoDTO dtoR = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ONE);
	    ps.setInt(1, dto.getEntidad().getIdProducto());
	    rs = ps.executeQuery();
	    if(rs.next()){
		dtoR = new ProductoDTO();
		dtoR.getEntidad().setIdProducto(rs.getInt(1));
		dtoR.getEntidad().setNombreProducto(rs.getString(2));
		dtoR.getEntidad().setDescripcionProducto(rs.getString(3));
		dtoR.getEntidad().setCostoUnitario(rs.getFloat(4));
		dtoR.getEntidad().setPrecioUnitario(rs.getFloat(5));
		dtoR.getEntidad().setIdCategoria(rs.getInt(6));
	    }
	} finally{
	    if(rs != null) rs.close();
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}
	return dtoR;
    }
    
    public ArrayList<ProductoDTO> selectAll() throws SQLException{
	ArrayList<ProductoDTO> lista = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    conn = ConexionDB.getConnection();
	    ps = conn.prepareStatement(SQL_SELECT_ALL);
	    rs = ps.executeQuery();
	    while(rs.next()){
		ProductoDTO dto = new ProductoDTO();
		dto.getEntidad().setIdProducto(rs.getInt(1));
		dto.getEntidad().setNombreProducto(rs.getString(2));
		dto.getEntidad().setDescripcionProducto(rs.getString(3));
		dto.getEntidad().setCostoUnitario(rs.getFloat(4));
		dto.getEntidad().setPrecioUnitario(rs.getFloat(5));
		dto.getEntidad().setIdCategoria(rs.getInt(6));
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
