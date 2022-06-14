package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.EntradaDAO;
import com.ipn.mx.modelo.dto.EntradaDTO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
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

public class EntradaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String accion = request.getParameter("accion");
	switch (accion) {
	    case "listar":
		listarEntradas(request, response);
		break;
	    case "crear":
		crearEntrada(request,response);
		break;
	    case "guardar":
		guardarEntrada(request,response);
		break;
	    case "editar":
		editarEntrada(request,response);
		break;
	    case "actualizar":
		actualizarEntrada(request,response);
		break;
	    case "reportar":
		reportarEntrada(request,response);
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

    private void listarEntradas(HttpServletRequest request, HttpServletResponse response) {
	try {
	    EntradaDAO edao = new EntradaDAO();
	    ArrayList<EntradaDTO> lista = edao.selectAll();
	    request.setAttribute("entradas", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/entrada/listaEntrada.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void crearEntrada(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ArrayList<ProductoDTO> lista = pdao.selectAll();
	    request.setAttribute("productos", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/entrada/crearEntrada.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }

    private void guardarEntrada(HttpServletRequest request, HttpServletResponse response) {
	try {
	    EntradaDAO edao = new EntradaDAO();
	    EntradaDTO edto = new EntradaDTO();
	    String cantidad = request.getParameter("cantidad");
	    int cantidadEntrada = Integer.parseInt(cantidad);
	    String idP = request.getParameter("producto");
	    int idProducto = Integer.parseInt(idP);
	    edto.getEntidad().setCantidadEntrada(cantidadEntrada);
	    edto.getEntidad().setIdProducto(idProducto);
	    edao.create(edto);
	    request.setAttribute("msg", "Entrada Añadida Correctamente");
	    request.setAttribute("tipo", "success");
	    listarEntradas(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void editarEntrada(HttpServletRequest request, HttpServletResponse response) {
	try {
	    EntradaDAO edao = new EntradaDAO();
	    EntradaDTO edto = new EntradaDTO();
	    ProductoDAO pdao = new ProductoDAO();
	    ArrayList<ProductoDTO> lista = pdao.selectAll();
	    String id = request.getParameter("id");
	    int idEntrada = Integer.parseInt(id);
	    edto.getEntidad().setIdEntrada(idEntrada);
	    EntradaDTO edtoR = edao.selectOne(edto);
	    request.setAttribute("productos", lista);
	    request.setAttribute("entrada", edtoR);
	    RequestDispatcher rd = request.getRequestDispatcher("/entrada/editarEntrada.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void actualizarEntrada(HttpServletRequest request, HttpServletResponse response) {
	try {
	    EntradaDAO edao = new EntradaDAO();
	    EntradaDTO edto = new EntradaDTO();
	    String id = request.getParameter("id");
	    int idEntrada = Integer.parseInt(id);
	    String cantidad = request.getParameter("cantidad");
	    int cantidadEntrada = Integer.parseInt(cantidad);
	    String idP = request.getParameter("producto");
	    int idProducto = Integer.parseInt(idP);
	    edto.getEntidad().setIdEntrada(idEntrada);
	    edto.getEntidad().setCantidadEntrada(cantidadEntrada);
	    edto.getEntidad().setIdProducto(idProducto);
	    edao.update(edto);
	    request.setAttribute("msg", "Entrada Actualizada Correctamente");
	    request.setAttribute("tipo", "success");
	    listarEntradas(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void reportarEntrada(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ServletOutputStream sos = null;
	    sos = response.getOutputStream();
	    File reporte;
	    byte[] b;
	    Map parameter = new HashMap();
	    
	    reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/repEntrada.jasper"));
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
