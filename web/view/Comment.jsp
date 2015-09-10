<%-- 
    Document   : Comment
    Created on : Sep 7, 2015, 2:21:19 PM
    Author     : calimero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/weblog.css" />
        <title>Comment page</title>
    </head>
    <body>
        <h1>Comment</h1>
        <form action="Comment" method="POST">
            <label for="comm">Comment</label>
            <textarea cols="40" rows="5" name="comm"></textarea>
            <input type="hidden" name="pid" value="<% out.print(request.getParameter("pid")); %>">
            <input type="submit" value="add comment">
        </form>        
    </body>
</html>
