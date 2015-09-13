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
                var pid = responseXML.getElementsByTagName("pid")[0].childNodes[0].nodeValue;
                var comments = responseXML.getElementsByTagName("comment");
                console.log(comments.length);
                for (loop = 0; loop < comments.length; loop++) {

                    var datum = comments[loop].getElementsByTagName("date")[0].childNodes[0].nodeValue;
                    var text = comments[loop].getElementsByTagName("text")[0].childNodes[0].nodeValue;
                    appendComment(datum, text, pid);

                }
            }
            function appendComment(dat, txt, p) {
                var html = document.getElementById("comment" + p).innerHTML;
                html = html + "<div id='time'>" + dat + "</div>";
                html = html + "<div id='content'>"+ txt + "</div>";
                document.getElementById("comment" + p).innerHTML = html;
            }
            function doComment(postid) {
                var url = "Commentjs?comm=" + encodeURI(document.getElementById(postid).value) + "&postid=" + postid;
                var req = getXHR();
                req.onreadystatechange = function ()
                {
                    processRequestChange(req);
                }
                req.open("GET", url, true);
                req.send(null);
                document.getElementById(postid).value = "";

            }
        </script>
    </head>
    <body>
        <a href="/Weblog"> Home </a><br>
        <h1>WebLog form</h1>
        <%
            List<Posting> posts = (List<Posting>) request.getAttribute("postings");
            for (Posting s : posts) {
                out.print("<div id='time'>" + s.getDate() + "</div>");
                out.print("<div id='title'>" + s.getTitle() + "</div>");
                out.print("<div id='content'>" + s.getContent() + "</div>");
                out.print("<div id='comment" + s.getId() + "'>");
                for (Comment c : s.getComments()) {
                    out.print("<div id='time'>" + c.getDate() + "</div>");
                    out.print("<div id='content'>" + c.getContent() + "</div>");
                }     
                out.print("</div>");
                out.print("<p id='addtext'></p>");
                out.print("<form id='commentForm' action='' method='GET'>");
                out.print("<label for='comm'>Comment: </label>");
                out.print("<textarea cols='40' rows='5' id='" + s.getId() + "' align='bottom'></textarea>");
                out.print("<input type='hidden' name='pid' value=''>");
                out.print("<input type='button' value='add comment' onclick=doComment(" + s.getId() + ")>");
                out.print("</form>");
                out.print("<p />");
                out.print("<hr />");
            }
        %>
    </body>
</html>
