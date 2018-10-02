package com.demo.service;

import com.demo.dto.Tree;
import com.demo.dto.TreeNode;


/**
 * 
 * Service to List directories
 * @author Shubham Kadlag
 *
 */
public class LsServiceImpl implements Service {
	
	   public String execute(String command, Tree tree){
		    
		   StringBuilder sb= new StringBuilder("DIRS: ");
		   
		   TreeNode curr = tree.getCurr(); 
		   
		   if(!curr.hasChildren()) return "No Directories Present";
		   
		   for(TreeNode temp : curr.getChildren()) {
			   
			   sb.append(" "+temp.getData()+" ");
			   
		   }   
		   
		   
		   return sb.toString();
		   }

		   @Override
		   public String getName() {
		      return "ls";
		   }

}
