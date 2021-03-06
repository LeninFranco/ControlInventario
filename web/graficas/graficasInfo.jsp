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
                            <h2>Gr??ficas</h2>
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
                        <div class="container text-center">
                            <div class="row">
                                <div class="col-lg-12">
                                    <h1 class="font-weight-bold mb-0">Consulta de gr??ficas</h1>
                                    <p class="lead text-muted">En esta secci??n podr?? apreciar las gr??ficas sobre informaci??n relevante del inventario</p>
                                </div>
                            </div>
                        </div>
                    </section>

                    <section class="bg-light py-3">
                        <div class="container">
                            <div class="row pt-2">
                                <div class="col-12 text-center">
                                    <img src="grafica1.png"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    
                                </div>
                            </div>
                            <div class="row pt-2">
                                <div class="col-12">
                                    <div class="col-12 text-center">
                                        <img class="img-fluid" src="grafica2.png"/>
                                    </div>
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

