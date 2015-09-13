/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Posting;
import service.WebLogService;

/**
 *
 * @author calimero
 */
public class WeblogAdm extends Command {

    private WebLogService service;

    public WeblogAdm(WebLogService ws, HttpServletRequest request, HttpServletResponse response) {
        super(ws, request, response);
        this.service = ws;
    }

    @Override
    public void execute() {

        if ("GET".equals(request.getMethod())) {
            RequestDispatcher view = null;
            HttpSession session = request.getSession();
            request.setAttribute("postings", service.getPostings());
            if (session.getAttribute("mode") != null) {
                if (session.getAttribute("mode").equals("basic")) {
                    view = request.getRequestDispatcher("view/WeblogAdm.jsp");
                } else {
                    view = request.getRequestDispatcher("view/WeblogAdm.jsp");
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
        } else {
            String action = request.getParameter("action");

            if (action.equals("add")) {
                String title = request.getParameter("title");
                String post = request.getParameter("post");
                service.addPosting(new Posting(title, post));
            } else if (action.endsWith("update")) {
                String pid = request.getParameter("pid");
                for (Posting p : service.getPostings()) {
                    if (p.getId() == Long.parseLong(pid)) {
                        p.setContent(request.getParameter("post"));
                    }
                }
            } else if (action.equals("delete")) {
                service.removePosting(Long.parseLong(request.getParameter("pid")));
            }

            List<Posting> pos = service.getPostings();

            request.setAttribute("postings", pos);
            RequestDispatcher view = request.getRequestDispatcher("view/WebLog.jsp");
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
