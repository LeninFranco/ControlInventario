package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
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

public class ProductoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String accion = request.getParameter("accion");
	switch (accion) {
	    case "listar":
		listarProductos(request, response);
		break;
	    case "crear":
		crearProductos(request, response);
		break;
	    case "guardar":
		guardarProductos(request, response);
		break;
	    case "eliminar":
		eliminarProductos(request, response);
		break;
	    case "editar":
		editarProductos(request, response);
		break;
	    case "actualizar":
		actualizarProductos(request, response);
		break;
	    case "reportar":
		reportarProductos(request, response);
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

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ArrayList<ProductoDTO> lista = pdao.selectAll();
	    request.setAttribute("productos", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/producto/listaProducto.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void crearProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    CategoriaDAO cdao = new CategoriaDAO();
	    ArrayList<CategoriaDTO> lista = cdao.selectAll();
	    request.setAttribute("categorias", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/producto/crearProducto.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void guardarProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ProductoDTO pdto = new ProductoDTO();
	    String nombreProducto = request.getParameter("nombre");
	    String descripcionProducto = request.getParameter("descripcion");
	    String costo = request.getParameter("costo");
	    float costoUnitario = Float.parseFloat(costo);
	    String precio = request.getParameter("precio");
	    float precioUnitario = Float.parseFloat(precio);
	    String idC = request.getParameter("categoria");
	    int idCategoria = Integer.parseInt(idC);
	    pdto.getEntidad().setNombreProducto(nombreProducto);
	    pdto.getEntidad().setDescripcionProducto(descripcionProducto);
	    pdto.getEntidad().setCostoUnitario(costoUnitario);
	    pdto.getEntidad().setPrecioUnitario(precioUnitario);
	    pdto.getEntidad().setIdCategoria(idCategoria);
	    pdao.create(pdto);
	    request.setAttribute("msg", "Producto Añadido Correctamente");
	    request.setAttribute("tipo", "success");
	    listarProductos(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void eliminarProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ProductoDTO pdto = new ProductoDTO();
	    String id = request.getParameter("id");
	    int idProducto = Integer.parseInt(id);
	    pdto.getEntidad().setIdProducto(idProducto);
	    pdao.delete(pdto);
	    request.setAttribute("msg", "Producto Eliminado Correctamente");
	    request.setAttribute("tipo", "danger");
	    listarProductos(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void editarProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ProductoDTO pdto = new ProductoDTO();
	    CategoriaDAO cdao = new CategoriaDAO();
	    ArrayList<CategoriaDTO> lista = cdao.selectAll();
	    String id = request.getParameter("id");
	    int idProducto = Integer.parseInt(id);
	    pdto.getEntidad().setIdProducto(idProducto);
	    ProductoDTO pdtoR = pdao.selectOne(pdto);
	    request.setAttribute("categorias", lista);
	    request.setAttribute("producto", pdtoR);
	    RequestDispatcher rd = request.getRequestDispatcher("/producto/editarProducto.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void actualizarProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ProductoDTO pdto = new ProductoDTO();
	    String id = request.getParameter("id");
	    int idProducto = Integer.parseInt(id);
	    String nombreProducto = request.getParameter("nombre");
	    String descripcionProducto = request.getParameter("descripcion");
	    String costo = request.getParameter("costo");
	    float costoUnitario = Float.parseFloat(costo);
	    String precio = request.getParameter("precio");
	    float precioUnitario = Float.parseFloat(precio);
	    String idC = request.getParameter("categoria");
	    int idCategoria = Integer.parseInt(idC);
	    pdto.getEntidad().setIdProducto(idProducto);
	    pdto.getEntidad().setNombreProducto(nombreProducto);
	    pdto.getEntidad().setDescripcionProducto(descripcionProducto);
	    pdto.getEntidad().setCostoUnitario(costoUnitario);
	    pdto.getEntidad().setPrecioUnitario(precioUnitario);
	    pdto.getEntidad().setIdCategoria(idCategoria);
	    pdao.update(pdto);
	    request.setAttribute("msg", "Producto Actualizado Correctamente");
	    request.setAttribute("tipo", "success");
	    listarProductos(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void reportarProductos(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ServletOutputStream sos = null;
	    sos = response.getOutputStream();
	    File reporte;
	    byte[] b;
	    Map parameter = new HashMap();
	    
	    reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/repProducto.jasper"));
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
