<%-- 
    Document   : index
    Created on : Sep 2, 2015, 11:48:08 AM
    Author     : Ko Litjens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/weblog.css" />
        <title>Weblog ESW</title>
    </head>
    <body>
        <% session = request.getSession();
        String s = "";
        if (session.getAttribute("mode") != null && session.getAttribute("mode").equals("advanced")) s = "WeblogAdmAdv";
        else s = "WeblogAdm";
        %>
        <h1>Initial form</h1>
        <a href=<% out.print(s); %> >Weblog Administrator page</a><br>
        <a href="WebLog">Show all blogs</a><br>
    </body>
</html>
