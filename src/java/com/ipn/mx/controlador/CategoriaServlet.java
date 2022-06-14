package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
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

public class CategoriaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String accion = request.getParameter("accion");
	switch (accion) {
	    case "listar":
		listarCategorias(request, response);
		break;
	    case "crear":
		crearCategoria(request,response);
		break;
	    case "guardar":
		guardarCategoria(request,response);
		break;
	    case "eliminar":
		eliminarCategoria(request,response);
		break;
	    case "editar":
		editarCategoria(request,response);
		break;
	    case "actualizar":
		actualizarCategoria(request,response);
		break;
	    case "reportar":
		reportarCategoria(request, response);
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

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) {
	
	try {
	    CategoriaDAO cdao = new CategoriaDAO();
	    ArrayList<CategoriaDTO> lista = cdao.selectAll();
	    request.setAttribute("categorias", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/categoria/listaCategoria.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }

    private void crearCategoria(HttpServletRequest request, HttpServletResponse response) {
	try {
	    RequestDispatcher rd = request.getRequestDispatcher("/categoria/crearCategoria.jsp");
	    rd.forward(request, response);
	} catch (ServletException | IOException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void guardarCategoria(HttpServletRequest request, HttpServletResponse response) {
	try {
	    CategoriaDAO cdao = new CategoriaDAO();
	    CategoriaDTO cdto = new CategoriaDTO();
	    String nombreCategoria = request.getParameter("nombre");
	    cdto.getEntidad().setNombreCategoria(nombreCategoria);
	    cdao.create(cdto);
	    request.setAttribute("msg", "Categoria Añadida Correctamente");
	    request.setAttribute("tipo", "success");
	    listarCategorias(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
	try {
	    CategoriaDAO cdao = new CategoriaDAO();
	    CategoriaDTO cdto = new CategoriaDTO();
	    String id = request.getParameter("id");
	    int idCategoria = Integer.parseInt(id);
	    cdto.getEntidad().setIdCategoria(idCategoria);
	    cdao.delete(cdto);
	    request.setAttribute("msg", "Categoria Eliminada Correctamente");
	    request.setAttribute("tipo", "danger");
	    listarCategorias(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void editarCategoria(HttpServletRequest request, HttpServletResponse response) {
	try {
	    CategoriaDAO cdao = new CategoriaDAO();
	    CategoriaDTO cdto = new CategoriaDTO();
	    String id = request.getParameter("id");
	    int idCategoria = Integer.parseInt(id);
	    cdto.getEntidad().setIdCategoria(idCategoria);
	    CategoriaDTO cdtoR = cdao.selectOne(cdto);
	    request.setAttribute("categoria", cdtoR);
	    RequestDispatcher rd = request.getRequestDispatcher("/categoria/editarCategoria.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
	try {
	    CategoriaDAO cdao = new CategoriaDAO();
	    CategoriaDTO cdto = new CategoriaDTO();
	    String id = request.getParameter("id");
	    int idCategoria = Integer.parseInt(id);
	    String nombreCategoria = request.getParameter("nombre");
	    cdto.getEntidad().setIdCategoria(idCategoria);
	    cdto.getEntidad().setNombreCategoria(nombreCategoria);
	    cdao.update(cdto);
	    request.setAttribute("msg", "Categoria Actualizada Correctamente");
	    request.setAttribute("tipo", "success");
	    listarCategorias(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void reportarCategoria(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ServletOutputStream sos = null;
	    sos = response.getOutputStream();
	    File reporte;
	    byte[] b;
	    Map parameter = new HashMap();
	    
	    reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/repCategoria.jasper"));
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
