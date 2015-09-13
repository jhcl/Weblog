/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;
import model.Posting;
import service.WebLogService;

/**
 *
 * @author calimero
 */
public class Commentjs extends Command {

    private WebLogService service;

    public Commentjs(WebLogService ws, HttpServletRequest request, HttpServletResponse response) {
        super(ws, request, response);
        this.service = ws;
    }

    @Override
    public void execute() {
        System.out.println("admadv post");
        String pid = request.getParameter("postid");
        StringBuilder sb = new StringBuilder();
        for (Posting p : service.getPostings()) {

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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}