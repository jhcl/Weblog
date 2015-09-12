<%-- 
    Document   : WeblogAdmAdv
    Created on : Sep 12, 2015, 1:09:45 AM
    Author     : calimero
--%>

<%@page import="model.Posting"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/weblog.css" />
        <title>Weblog adm advanced page</title>
    </head>
    <body>
        <% session.setAttribute("mode", "advanced"); %>
        <a href="view/index.jsp"> Home </a><br>
               
        <h1>WeblogAdm advanced form</h1>
        <a href="WeblogAdm"> basic </a><br> 
        <form action="WeblogAdm" method="POST">
            <input type="hidden" value="add" name="action">
            <label for="title">Title</label>
            <input type="text" name="title"><br><br>
            <label for="post">Post</label>
            <textarea cols="40" rows="5" name="post"></textarea>
            <input type="submit" value="add posting" name="add">
        </form>
        <%
            List<Posting> pos = (List) request.getAttribute("postings");
            for (Posting p : pos) {
                out.print("<p>");
                out.print("<form action='WeblogAdm' method='POST'>");
                out.print("<div id='time'>" + p.getDate() + "</div>");
                out.print("<textarea name='post'>" + p.getContent() + "</textarea>");
                out.print("<input type='hidden' name='pid' value='" + p.getId() + "'>");
                out.print("<input type='radio' value='update' name='action' checked>update");
                out.print("<input type='radio' value='delete' name='action'>delete");
                out.print("<input type='submit' value='submit' name='submit'>");
                out.print("</form>");
                out.print("</p>");
            }
        %>
    </body>
</html>
