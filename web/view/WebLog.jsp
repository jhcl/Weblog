<%-- 
    Document   : WebLog
    Created on : Sep 2, 2015, 1:10:11 PM
    Author     : calimero
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="model.Comment"%>
<%@page import="model.Posting"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/weblog.css" />
        <title>WebLog form</title>
        <script>
            function getXHR() {
                if (window.XMLHttpRequest) {
                    return new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            function processRequestChange(req) {
                if (req.readyState === 4) {
                    if (req.status === 200) {
                        parseResponse(req.responseXML);
                    }
                }
            }

            function parseResponse(responseXML) {
                var comments = responseXML.getElementsByTagName("postid")[0];

                for (loop = 0; loop < comments.childNodes.length; loop++) {

                    var comment = comments.childNodes[loop];
                    var name = comment.getElementsByTagName("name")[0];

                    appendComment(name.childNodes[0].nodeValue);

                }
            }
            function appendComment(name) {
                var html = document.getElementById("comment").innerHTML;
                html = html + '<tr>';
                html = html + name;
                html = html + '</tr>';
                document.getElementById("comms").innerHTML = html;
            }
            function doComment(postid) {
                var url = "Commentjs?comm=" + encodeURI(document.getElementById(postid).value) + "&postid=" +postid;
                var req = getXHR();
                req.onreadystatechange = function ()
                {
                    processRequestChange(req);
                }
                req.open("GET", url, true);
                req.send(null);

            }
        </script>
    </head>
    <body>
        <h1>WebLog form</h1>
        <%
            List<Posting> posts = (List<Posting>) request.getAttribute("postings");
            for (Posting s : posts) {
                out.print("<div id='time'>" + s.getDate() + "</div>");
                out.print("<div id='title'>" + s.getTitle() + "</div>");
                out.print("<div id='content'>" + s.getContent() + "</div>");
                out.print("<div id='comment'></div>");
                out.print("<p id='addtext'></p>");
                out.print("<form id='commentForm' action='' method='GET'>");
                out.print("<label for='comm'>Commentjs</label>");
                out.print("<textarea cols='40' rows='5' id='" + s.getId() + "' align='bottom'></textarea>");
                out.print("<input type='hidden' name='pid' value=''>");
                out.print("<input type='button' value='add comment' onclick=doComment(" + s.getId() + ")>");
                out.print("</form>");
                out.print("<a href='Comment?pid=" + s.getId() + "'>Comment</a>");
                for (Comment c : s.getComments()) {
                    out.print("<div id='comments'>");
                    out.print("<div id='time'>" + c.getDate() + "</div>");
                    out.print("<div id='content'>" + c.getContent() + "</div>");
                    out.print("</div>");
                }
                out.print("<p>");
            }
        %>
    </body>
</html>
