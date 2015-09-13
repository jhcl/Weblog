/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.WebLogService;

/**
 *
 * @author calimero
 */
public abstract class Command {
    
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    public Command(WebLogService ws, HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    
    public abstract void execute();
    
    public void setReq(HttpServletRequest req) {
        this.request = req;
    }
    
    public void setResp(HttpServletResponse resp) {
        this.response = resp;
    }    
    
}
