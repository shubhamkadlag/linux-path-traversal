package com.demo.service;

import com.demo.dto.Tree;
import com.demo.dto.TreeNode;

/**
 * SessionService to handle the session. Currently it only supports to clear the session.
 * @author Shubham Kadlag
 *
 */
public class SessionServiceImpl implements Service {
	
	   public String execute(String command, Tree tree){
		   
		    command=command.trim();
		    
		    if("clear".equals(command)) {
		    tree.setRoot(new TreeNode("/", new TreeNode()));
		    tree.setCurr(tree.getRoot());
		    return "Session Cleared Successfully";
		    }
		    return "Session Not Cleared. Please check command. Usage: session clear";
		    
	   }

		   @Override
		   public String getName() {
		      return "session";
		   }

		 
}
