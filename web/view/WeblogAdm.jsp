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
        <title>Weblog adm page</title>
    </head>
    <body>
        <h1>WeblogAdm form</h1>
        <form action="WeblogAdm" method="POST">
            <label for="title">Title</label>
            <input type="text" name="title"><br><br>
            <label for="post">Post</label>
            <textarea cols="40" rows="5" name="post"></textarea>
            <input type="submit" value="add posting">
        </form>
    </body>
</html>
