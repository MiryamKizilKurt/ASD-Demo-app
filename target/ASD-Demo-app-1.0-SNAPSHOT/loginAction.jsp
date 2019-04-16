<%@page import="asd.demo.model.dao.MongoDBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="asd.demo.controller.*" import="asd.demo.model.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Action</title>
    </head>
    <body>
        <% String filePath = application.getRealPath("WEB-INF/users.xml");%>
        <jsp:useBean id="userApp" class="asd.demo.controller.UserApplication" scope="application">
            <jsp:setProperty name="userApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%Users users = userApp.getUsers(); %>
        <%Validator validator = new Validator();%>
        <%
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = users.login(email, password);

            MongoDBConnector connector = (MongoDBConnector)session.getAttribute("connector");
            
            if (validator.checkEmpty(email, password)) {
                session.setAttribute("emptyErr", "All fields are mendatory");
                response.sendRedirect("index.jsp");
            } else if (!validator.validateEmail(email)) {
                session.setAttribute("emailErr", "Incorrect email format");
                response.sendRedirect("index.jsp");
            } else if (!validator.validatePassword(password)) {
                session.setAttribute("passErr", "Incorrect password format");
                response.sendRedirect("index.jsp");
            } else if (user != null) {
                session.setAttribute("user", user);
                response.sendRedirect("main.jsp");
            } else {
                session.setAttribute("existErr", "User does not exist!");
                response.sendRedirect("main.jsp");
            }

        %>
    </body>
</html>
