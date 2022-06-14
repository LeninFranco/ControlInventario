package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.InventarioDAO;
import com.ipn.mx.modelo.dao.ProdXCatDAO;
import com.ipn.mx.modelo.entidades.Inventario;
import com.ipn.mx.modelo.entidades.ProdXCat;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class GraficasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String accion = request.getParameter("accion");
	switch (accion) {
	    case "graficar":
		graficarInfo(request, response);
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

    private void graficarInfo(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ProdXCatDAO dao1 = new ProdXCatDAO();
	    InventarioDAO dao2 = new InventarioDAO();
	    ArrayList<ProdXCat> lista1 = dao1.selectAll();
	    ArrayList<Inventario> lista2 = dao2.selectAll();
	    
	    DefaultPieDataset pie = new DefaultPieDataset();
	    for(ProdXCat pxc : lista1){
		pie.setValue(pxc.getNombreCategoria(), pxc.getCantidad());
	    }
	    JFreeChart chart1 = ChartFactory.createPieChart("Productos por Categoria", pie, true, true, Locale.getDefault());
	    String archivo1 = getServletConfig().getServletContext().getRealPath("/grafica1.png");
	    ChartUtils.saveChartAsPNG(new File(archivo1), chart1, 800, 600);
	    
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    for(Inventario inv : lista2){
		dataset.setValue(inv.getCantidadActual(), "Inventario", inv.getNombreCategoria());
	    }
	    JFreeChart chart2 = ChartFactory.createBarChart("Inventario Actual", "Productos", "Cantidad Actual", dataset, PlotOrientation.VERTICAL, false, true, false);
	    String archivo2 = getServletConfig().getServletContext().getRealPath("/grafica2.png");
	    ChartUtils.saveChartAsPNG(new File(archivo2), chart2, 800, 600);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/graficas/graficasInfo.jsp");
	    rd.forward(request, response);
	} catch (SQLException | IOException | ServletException ex) {
	    Logger.getLogger(GraficasServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
