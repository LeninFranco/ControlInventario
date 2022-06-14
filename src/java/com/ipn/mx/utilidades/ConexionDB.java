package com.ipn.mx.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
    
    public static Connection getConnection(){
	String url = "jdbc:mysql://bksjxhrgjsbvcfk7c7o5-mysql.services.clever-cloud.com:3306/bksjxhrgjsbvcfk7c7o5";
	String usuario = "uj4pbhqi5gngkf1g";
	String password = "Vw5YOL9sq2ImHUzIhslF";
	String driverDB = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;
	try {
	    Class.forName(driverDB);
	    conn = DriverManager.getConnection(url, usuario, password);
	} catch (ClassNotFoundException | SQLException ex) {
	    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
	}
	return conn;
    }
    
}
