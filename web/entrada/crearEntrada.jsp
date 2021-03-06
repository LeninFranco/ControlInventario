<%@page import="com.ipn.mx.modelo.dto.ProductoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- Styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

        <!-- Google fonts -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,700&display=swap" rel="stylesheet">

        <!-- Ionic icons -->
        <link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">

        <title>Gestor de Inventario</title>
    </head>

    <body>
        <%
            HttpSession sesion = request.getSession();
            if(sesion.getAttribute("loged") == null || sesion.getAttribute("loged").equals("0")){
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        %>
        <div class="d-flex" id="content-wrapper">

            <!-- Sidebar -->
            <div id="sidebar-container" class="bg-primary">
                <div class="logo">
                    <h4 class="text-light font-weight-bold mb-0">Inventarios ESCOM</h4>
                </div>
                <div class="menu">
                    <a href="home.jsp" class="d-block text-light p-3 border-0"><i class="icon ion-md-home lead mr-2"></i>
                        Inicio</a>
                    <a href="ProductoServlet?accion=listar" class="d-block text-light p-3 border-0"><i
                            class="icon ion-md-appstore lead mr-2"></i>
                        Productos</a>
                    <a href="CategoriaServlet?accion=listar" class="d-block text-light p-3 border-0"><i
                            class="icon ion-md-filing lead mr-2"></i>
                        Categorias</a>
                    <a href="EntradaServlet?accion=listar" class="d-block text-light p-3 border-0"><i
                            class="icon ion-md-arrow-round-up lead mr-2"></i>
                        Entradas</a>
                    <a href="SalidaServlet?accion=listar" class="d-block text-light p-3 border-0"> <i
                            class="icon ion-md-arrow-round-down lead mr-2"></i>
                        Salidas</a>
                    <a href="InventarioServlet?accion=listar" class="d-block text-light p-3 border-0"> <i
                            class="icon ion-md-clipboard lead mr-2"></i>
                        Inventario Actual</a>
                    <a href="GraficasServlet?accion=graficar" class="d-block text-light p-3 border-0"><i class="icon ion-md-pie lead mr-2"></i>
                        Gr??ficas</a>
                </div>
            </div>
            <!-- Fin sidebar -->

            <div class="w-100">

                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container">

                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <h2>Entradas</h2>
                            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                                <li class="nav-item dropdown">
                                    <a class="nav-link text-dark dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Administrador
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="logout.jsp">Cerrar sesi??n</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Fin Navbar -->

                <!-- Page Content -->
                <div id="content">
                    <section class="bg-light py-3">
                        <div class="container">
                            <div class="card rounded-0">
                                <div class="card-body">
                                    <form action="EntradaServlet?accion=guardar" method="post">
                                        <div class="mb-3">
                                            <label>Cantidad de Entrada</label>
                                        </div>
                                        <div class="mb-3">
                                            <input type="number" min="0" max="9999" step="1" name="cantidad" class="form-control" required>
                                        </div>
                                        <div class="mb-3">
                                            <label>Producto</label>
                                        </div>
                                        <div class="mb-3">
                                            <select class="form-control" name="producto">
                                                <%
                                                  int i = 1;
                                                  ArrayList<ProductoDTO> productos = (ArrayList<ProductoDTO>) request.getAttribute("productos");
                                                  for(ProductoDTO producto : productos){
                                                      if(i == 1){
                                                %>
                                                        <option value="<%= producto.getEntidad().getIdProducto() %>" selected><%= producto.getEntidad().getNombreProducto() %></option>
                                                <%
                                                      }  else {
                                                %>
                                                        <option value="<%= producto.getEntidad().getIdProducto() %>"><%= producto.getEntidad().getNombreProducto() %></option>
                                                <%
                                                      }  
                                                %>        
                                                <%
                                                        i = 0;
                                                    }  
                                                %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <button class="btn btn-primary w-100" type="submit">Guardar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>

            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"
        integrity="sha256-R4pqcOYV8lt7snxMQO/HSbVCFRPMdrhAFMH+vr9giYI=" crossorigin="anonymous"></script>
    </body>

</html>
