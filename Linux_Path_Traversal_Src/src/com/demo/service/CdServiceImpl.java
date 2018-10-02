package com.demo.service;

import com.demo.dto.Tree;
import com.demo.dto.TreeNode;

/**
 * 
 * Service to Change directory
 * @author Shubham Kadlag
 *
 */
public class CdServiceImpl implements Service {
	
	   /** (non-Javadoc)
	 * @see com.demo.service.Service#execute(java.lang.String, com.demo.dto.Tree)
	 * 
	 * 
	 */
	public String execute(String command, Tree tree){
		   	StringBuilder sb=new StringBuilder("");
		   	if(command==null) return "Error: Path does not exist";
		   	
		   	command=command.trim();			
		   	
		   	command=command.split("\\s+")[0];
		   	
		   	if(command.equals(tree.getRoot().getData())) {
		   		
		   		tree.setCurr(tree.getRoot() );
		   		return "SUCC: REACHED";
		   		
		   	}
		   	
		   	if(command.endsWith("/")) {
		   		command=command.substring(0, command.length()-1);
		   	}
		   	
		   	if(command.isEmpty()) return "Error: Path does not exist";
		   	
		   	if(command.startsWith("/")) {
		   		
		   		String commandedited=command.replaceFirst("/","");
		   		String path[]=commandedited.split("/");
		   		TreeNode root=tree.getRoot();
		   		TreeNode temp=root;
		   		for(int i=0; i<path.length ;i++) {
		   			
		   			temp=temp.getChild(new TreeNode(path[i],temp));
		   			
		   			if(temp==null)  return sb.append("Error : "+command+" directory does not exist").toString();
		   			
		   		}
		   		
		   		tree.setCurr(temp);
		   		return sb.append("SUCC: REACHED").toString();
		   		
		   	}else {
		   		
		   		String commandedited=command;
		   		String path[]=commandedited.split("/");
		   		TreeNode curr=tree.getCurr();
		   		TreeNode temp=curr;
		   		for(int i=0; i<path.length ;i++) {
		   			
		   			temp=temp.getChild(new TreeNode(path[i],temp));
		   			
		   			if(temp==null)  return sb.append("Error : "+command+" directory does not exist").toString();
		   			
		   		}	  
		   		
		   		tree.setCurr(temp);
		   		
		   		return sb.append("SUCC: REACHED").toString();
		   	}
		    	   
		      
		      
		   }

		   @Override
		   public String getName() {
		      return "cd";
		   }

		 
}
