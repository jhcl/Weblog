/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Comment;
import model.Posting;
import service.WebLogService;

/**
 *
 * @author calimero
 */
public class WeblogAdmAdv extends Command {

    private WebLogService service;

    public WeblogAdmAdv(WebLogService ws, HttpServletRequest request, HttpServletResponse response) {
        super(ws, request, response);
        this.service = ws;
    }

    @Override
    public void execute() {
        if ("GET".equals(request.getMethod())) {
            System.out.println("admadv get");
            RequestDispatcher view = null;
            HttpSession session = request.getSession();
            session.setAttribute("mode", "advanced");
            request.setAttribute("postings", service.getPostings());
            if (session.getAttribute("mode") != null) {
                if (session.getAttribute("mode").equals("basic")) {
                    view = request.getRequestDispatcher("view/WeblogAdm.jsp");
                } else {
                    view = request.getRequestDispatcher("view/WeblogAdmAdv.jsp");
                }
            } else {
                session.setAttribute("mode", "basic");
                view = request.getRequestDispatcher("view/WeblogAdm.jsp");
            }
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
