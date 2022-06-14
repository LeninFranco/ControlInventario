package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.InventarioDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dao.SalidaDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.dto.SalidaDTO;
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

public class SalidaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String accion = request.getParameter("accion");
	switch (accion) {
	    case "listar":
		listarSalidas(request, response);
		break;
	    case "crear":
		crearSalida(request,response);
		break;
	    case "guardar":
		guardarSalida(request,response);
		break;
	    case "editar":
		editarSalida(request,response);
		break;
	    case "actualizar":
		actualizarSalida(request,response);
		break;	
	    case "reportar":
		reportarSalida(request,response);
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

    private void listarSalidas(HttpServletRequest request, HttpServletResponse response) {
	try {
	    SalidaDAO sdao = new SalidaDAO();
	    ArrayList<SalidaDTO> lista = sdao.selectAll();
	    request.setAttribute("salidas", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/salida/listaSalida.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(SalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void crearSalida(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProductoDAO pdao = new ProductoDAO();
	    ArrayList<ProductoDTO> lista = pdao.selectAll();
	    request.setAttribute("productos", lista);
	    RequestDispatcher rd = request.getRequestDispatcher("/salida/crearSalida.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void guardarSalida(HttpServletRequest request, HttpServletResponse response) {
	try {
	    SalidaDAO sdao = new SalidaDAO();
	    SalidaDTO sdto = new SalidaDTO();
	    ProductoDTO pdto = new ProductoDTO();
	    ProductoDAO pdao = new ProductoDAO();
	    InventarioDAO dao = new InventarioDAO();
	    String cantidad = request.getParameter("cantidad");
	    int cantidadSalida = Integer.parseInt(cantidad);
	    String idP = request.getParameter("producto");
	    int idProducto = Integer.parseInt(idP);
	    pdto.getEntidad().setIdProducto(idProducto);
	    ProductoDTO pdtoR = pdao.selectOne(pdto);
	    float costoTotal = pdtoR.getEntidad().getCostoUnitario()*cantidadSalida;
	    float ventaTotal = pdtoR.getEntidad().getPrecioUnitario()*cantidadSalida;
	    sdto.getEntidad().setCantidadSalida(cantidadSalida);
	    sdto.getEntidad().setCostoTotal(costoTotal);
	    sdto.getEntidad().setVentaTotal(ventaTotal);
	    sdto.getEntidad().setIdProducto(idProducto);
	    Inventario inv = dao.selectOne(idProducto);
	    if(cantidadSalida > inv.getCantidadActual()){
		request.setAttribute("msg", "No hay suficiente existencia de este producto");
		request.setAttribute("tipo", "warning");
	    }
	    else{
		sdao.create(sdto);
		request.setAttribute("msg", "Salida Añadida Correctamente");
		request.setAttribute("tipo", "success");
	    } 
	    listarSalidas(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(SalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void editarSalida(HttpServletRequest request, HttpServletResponse response) {
	try {
	    SalidaDAO sdao = new SalidaDAO();
	    SalidaDTO sdto = new SalidaDTO();
	    ProductoDAO pdao = new ProductoDAO();
	    ArrayList<ProductoDTO> lista = pdao.selectAll();
	    String id = request.getParameter("id");
	    int idSalida = Integer.parseInt(id);
	    sdto.getEntidad().setIdSalida(idSalida);
	    SalidaDTO sdtoR = sdao.selectOne(sdto);
	    request.setAttribute("productos", lista);
	    request.setAttribute("salida", sdtoR);
	    RequestDispatcher rd = request.getRequestDispatcher("/salida/editarSalida.jsp");
	    rd.forward(request, response);
	} catch (SQLException | ServletException | IOException ex) {
	    Logger.getLogger(SalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void actualizarSalida(HttpServletRequest request, HttpServletResponse response) {
	try {
	    SalidaDAO sdao = new SalidaDAO();
	    SalidaDTO sdto = new SalidaDTO();
	    ProductoDTO pdto = new ProductoDTO();
	    ProductoDAO pdao = new ProductoDAO();
	    InventarioDAO dao = new InventarioDAO();
	    String id = request.getParameter("id");
	    int idSalida = Integer.parseInt(id);
	    String cantidad = request.getParameter("cantidad");
	    int cantidadSalida = Integer.parseInt(cantidad);
	    String idP = request.getParameter("producto");
	    int idProducto = Integer.parseInt(idP);
	    pdto.getEntidad().setIdProducto(idProducto);
	    ProductoDTO pdtoR = pdao.selectOne(pdto);
	    float costoTotal = pdtoR.getEntidad().getCostoUnitario()*cantidadSalida;
	    float ventaTotal = pdtoR.getEntidad().getPrecioUnitario()*cantidadSalida;
	    sdto.getEntidad().setIdSalida(idSalida);
	    sdto.getEntidad().setCantidadSalida(cantidadSalida);
	    sdto.getEntidad().setCostoTotal(costoTotal);
	    sdto.getEntidad().setVentaTotal(ventaTotal);
	    sdto.getEntidad().setIdProducto(idProducto);
	    Inventario inv = dao.selectOne(idProducto);
	    if(cantidadSalida > inv.getCantidadActual()){
		request.setAttribute("msg", "No hay suficiente existencia de este producto");
		request.setAttribute("tipo", "warning");
	    }
	    else{
		sdao.update(sdto);
		request.setAttribute("msg", "Salida Actualizada Correctamente");
		request.setAttribute("tipo", "success");
	    } 
	    listarSalidas(request, response);
	} catch (SQLException ex) {
	    Logger.getLogger(SalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void reportarSalida(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ServletOutputStream sos = null;
	    sos = response.getOutputStream();
	    File reporte;
	    byte[] b;
	    Map parameter = new HashMap();
	    
	    reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/repSalida.jasper"));
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
