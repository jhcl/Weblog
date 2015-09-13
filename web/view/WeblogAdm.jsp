<%-- 
    Document   : WeblogAdm
    Created on : Sep 2, 2015, 12:49:03 PM
    Author     : calimero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/weblog.css" />
        <title>Weblog adm basic page</title>
    </head>
    <body>
        <% session.setAttribute("mode", "basic"); %>
        <a href="/Weblog"> Home </a><br>
        <h1>WeblogAdm basic form</h1>
        <a href="WeblogAdmAdv"> advanced </a><br>
        <form action="WeblogAdm" method="POST">
            <input type="hidden" value="add" name="action">
            <label for="title">Title</label>
            <input type="text" name="title"><br><br>
            <label for="post">Post</label>
            <textarea cols="40" rows="5" name="post"></textarea>
            <input type="submit" value="add posting">
        </form>
    </body>
</html>
