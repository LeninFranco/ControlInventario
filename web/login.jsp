<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String user = request.getParameter("usuario");
            String password = request.getParameter("contrasena");
            HttpSession sesion = request.getSession();
            if(user.equals("admin") && password.equals("admin")){
                sesion.setAttribute("loged", "1");
                RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("msg", "Usuario o Contraseña Incorrectos");
                request.setAttribute("tipo", "danger");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        %>
    </body>
</html>
