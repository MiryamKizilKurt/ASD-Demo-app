<%@page import="asd.demo.model.*"%>
<!DOCTYPE html>
<html>
    <head>    
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js" ></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" ></script>
        <script src="animation.js"></script>
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <script type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript" src="js/animation.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css">   

    </head>

    <body onload="startTime()">           
        <div class="bs-docs-header" id="content" tabindex="-1">
            <span class="time" id="time" ></span>
            <div class="container">
                 <h1>ASD  Demo Application</h1>                        
                 <p class="p">Java Maven Web Application</p>                 
            </div>
            <% String filePath = application.getRealPath("WEB-INF/users.xml");%>
            <jsp:useBean id="userApp" class="asd.demo.controller.UserApplication" scope="application">
                <jsp:setProperty name="userApp" property="filePath" value="<%=filePath%>"/>
            </jsp:useBean>
            <%Users users = userApp.getUsers(); %>
            <%
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    String log = " &lt " + user.getName() + " &gt";

            %>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>                             
                        </button>
                        <a class="navbar-brand" href="#Myaccount-Div" data-toggle="tab" id="Myaccount-Tab">My Account</a>                      
                    </div>
                    <ul class="nav navbar-nav navbar-right">                            
                        <li><a href="logout.jsp" id="Logout-Tab" ><span style="color:#70bd24; font-size: 14px;">You are logging in as: <%=log%></span>&emsp;<span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                            <%} else {%>
                        <li><a href="logout.jsp" id="Logout-Tab" ><span style="color:#70bd24">User does not exist!</span>&emsp;<span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                            <%}%>
                    </ul> 
                </div>
            </nav>           
        </div> 
        <div class="container" style="width:102%; height: 75%;">
            <div class="row">                                  
                <div class="tab-content">
                    <iframe id="Display-frame" name="Display-frame" src="" frameborder="0"  allowFullScreen></iframe> 
                </div>                
            </div>
        </div>
        <div class="footer" >            
            <h2 id="buttons-options" class="title">Control Panel</h2>
            <div class="bs-glyphicons" style="padding-bottom:10px;">          
                <a class="btn btn-success glyphicon glyphicon-refresh" href="#" id="Default-Tab"></a>             
                <a class="btn btn-primary glyphicon glyphicon-save" href="https://github.com/Georges034302/lab_demo" target="_blank"></a>               
                <a class="btn btn-info glyphicon glyphicon-cloud" href="https://www.google.com/drive/" target="_blank"></a>            
                <a class="btn btn-warning glyphicon glyphicon-pencil" href="http://collabedit.com/" target="_blank"></a>           
                <a class="btn btn-danger glyphicon glyphicon-send" href="mailto: "></a>
            </div>
        </div>  
        <script src="webjars/jquery/2.1.4/jquery.js"></script>
        <script src="webjars/bootstrap/3.3.5/js/bootstrap.js"></script>
    </body>
</html>