/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.WebLogService;

/**
 *
 * @author calimero
 */
public class WebLog extends Command {

    private final WebLogService service;

    public WebLog(WebLogService ws, HttpServletRequest request, HttpServletResponse response) {
        super(ws, request, response);
        this.service = ws;
    }

    @Override
    public void execute() {
        try {
        RequestDispatcher view = request.getRequestDispatcher("view/WebLog.jsp");
        request.setAttribute("postings", service.getPostings());
        view.forward(request, response);
        } catch(ServletException | IOException se) {
            System.out.println(se.getMessage());
        }
    }

}
