/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.WebLogService;

/**
 *
 * @author calimero
 */
public class CommandFactory {
    
    private final Map<String, Command> commands;
    private static CommandFactory instance;
    private final WebLogService ws = new WebLogService();
    
    private CommandFactory() {
        
        commands = new HashMap<>();
        commands.put("/WeblogAdmAdv", new WeblogAdmAdv(ws, null, null));
        commands.put("/WeblogAdm", new WeblogAdm(ws, null, null));
        commands.put("/WebLog", new WebLog(ws, null, null));
        commands.put("/Commentjs", new Commentjs(ws, null, null));
        commands.put("NotFound", new NotFound(ws, null, null));
    }
    
    public static CommandFactory getCommandFactory() {
        if (instance != null) return instance;
        else return new CommandFactory();
    }
    
    public void registerCommand(String userPath, Command command) {
        this.commands.put(userPath, command);
    }
    
    public Command CreateCommand(HttpServletRequest request, HttpServletResponse response) {
        Command doComm = this.commands.get(request.getServletPath());
        if (doComm == null) return this.commands.get("NotFound");
        
        doComm.setReq(request);
        doComm.setResp(response);
        
        return doComm;
    }
    
}
