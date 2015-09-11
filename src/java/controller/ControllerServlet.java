/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;
import model.Posting;
import service.WebLogService;

/**
 *
 * @author calimero
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/WeblogAdm", "/WebLog", "/Comment", "/Commentjs"})
public class ControllerServlet extends HttpServlet {

    WebLogService ws = new WebLogService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //    processRequest(request, response);

        String userPath = request.getServletPath();
        if (userPath.equals("/WebLog")) {
            RequestDispatcher view = request.getRequestDispatcher("view/WebLog.jsp");

            request.setAttribute("postings", ws.getPostings());
            view.forward(request, response);
        } else if (userPath.equals("/WeblogAdm")) {
            RequestDispatcher view = request.getRequestDispatcher("view/WeblogAdm.jsp");
            view.forward(request, response);
        } else if (userPath.equals("/Comment")) {
            String pid = request.getParameter("pid");
            request.setAttribute("postings", ws.getPostings());
            RequestDispatcher view = request.getRequestDispatcher("view/Comment.jsp");
            view.forward(request, response);

        } else if (userPath.equals("/Commentjs")) {
            String pid = request.getParameter("postid");
            StringBuilder sb = new StringBuilder();
            for (Posting p : ws.getPostings()) {

                if (p.getId() == Long.parseLong(pid)) {
                    List<Comment> comm = p.getComments();
                    Comment c = new Comment(1L, request.getParameter("comm"));
                    comm.add(c);
                    p.setComments(comm);
                    sb.append("<postid>");
                    sb.append("<pid>").append(pid).append("</pid>");
                    sb.append("<comment>");
                    sb.append("<date>").append(c.getDate()).append("</date>");
                    sb.append("<text>").append(c.getContent()).append("</text>");
                    sb.append("</comment>");
                    sb.append("</postid>");
                }
            }

            response.setContentType("text/xml;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println(sb.toString());
                out.close();
            }
        } else {
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //    processRequest(request, response);
        String userPath = request.getServletPath();
        System.out.println("Userpath :" + userPath);
        List<Posting> pos;

        if (userPath.equals("/WeblogAdm")) {

            String title = request.getParameter("title");
            String post = request.getParameter("post");

            ws.addPosting(new Posting(title, post));
            pos = ws.getPostings();

            request.setAttribute("postings", pos);
            RequestDispatcher view = request.getRequestDispatcher("view/WebLog.jsp");
            view.forward(request, response);

        } else if (userPath.equals("/Comment")) {

            for (Posting p : ws.getPostings()) {

                if (p.getId() == Long.parseLong(request.getParameter("pid"))) {

                    List<Comment> comm = p.getComments();
                    comm.add(new Comment(1L, request.getParameter("comm")));
                    p.setComments(comm);
                }
            }
            pos = ws.getPostings();

            request.setAttribute("postings", pos);
            RequestDispatcher view = request.getRequestDispatcher("view/WebLog.jsp");
            view.forward(request, response);

        } else {
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
