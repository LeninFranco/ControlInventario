package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.InventarioDAO;
import com.ipn.mx.modelo.entidades.Inventario;
import com.ipn.mx.utilidades.ConexionDB;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class InventarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String accion = request.getParameter("accion");
	switch (accion) {
	    case "listar":
		listarInventario(request, response);
		break;
	    case "reportar":
		reportarInventario(request, response);
		break;
	    default:
		break;
	}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

    private void listarInventario(HttpServletRequest request, HttpServletResponse response) {
	try {
	    InventarioDAO idao = new InventarioDAO();
	    ArrayList<Inventario> lista = idao.selectAll();
	    request.setAttribute("inventario", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/inventario/listaInventario.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(InventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void reportarInventario(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ServletOutputStream sos = null;
	    sos = response.getOutputStream();
	    File reporte;
	    byte[] b;
	    Map parameter = new HashMap();
	    
	    reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/repInventario.jasper"));
	    b = JasperRunManager.runReportToPdf(reporte.getPath(), parameter, ConexionDB.getConnection());
	    
	    response.setContentType("application/pdf");
	    response.setContentLength(b.length);
	    sos.write(b,0,b.length);
	    sos.flush();
	    sos.close();
	} catch (IOException | JRException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
